package br.com.academiadev.BatataComBaconSpring.endpoint;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.BatataComBaconSpring.config.ServerResponse;
import br.com.academiadev.BatataComBaconSpring.dto.post.PasswordDTO;
import br.com.academiadev.BatataComBaconSpring.dto.post.PostUserDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.ResponseUserDTO;
import br.com.academiadev.BatataComBaconSpring.exception.OperacaoNaoSuportadaException;
import br.com.academiadev.BatataComBaconSpring.mapper.UserMapper;
import br.com.academiadev.BatataComBaconSpring.model.PasswordResetToken;
import br.com.academiadev.BatataComBaconSpring.model.User;
import br.com.academiadev.BatataComBaconSpring.repository.PasswordTokenRepository;
import br.com.academiadev.BatataComBaconSpring.service.UserService;
import br.com.academiadev.BatataComBaconSpring.service.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api("Endpoint de usuário")
public class UserEndpoint {

	@Autowired
	private UserService service;

	@Autowired
	private UserMapper mapper;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private PasswordTokenRepository tokenRepository;

	@ApiOperation(value = "Cria um usuario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuario criado com sucesso") })
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ResponseUserDTO save(@RequestBody @Valid PostUserDTO dto) {
		return mapper.toDTO(service.save(mapper.toUser(dto)));
	}

	// Esta interface está especificada para acesso somente aos ADMINS
	@ApiOperation(value = "Retorna a lista de usuarios")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso") })
	@GetMapping
	public Page<ResponseUserDTO> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "20") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return Utils.toPageDTO(service.findAll(pageable), mapper::toDTO);
	}

	@ApiOperation(value = "Retorna um usuário pelo Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Usuário encontrado com sucesso") })
	@GetMapping("/{idUser}")
	public ResponseUserDTO findById(@PathVariable("idUser") Long idUser) {
		verificaAutorizado(idUser);
		return mapper.toDTO(service.findById(idUser));
	}

	@ApiOperation(value = "Retorna o Usuário que está logado")
	@GetMapping("/whoami")
	public ResponseUserDTO whoami() {
		return mapper.toDTO(service.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
	}

	@ApiOperation(value = "Altera um usuário")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Usuário alterado com sucesso"), //
			@ApiResponse(code = 404, message = "Usuário não encontrado") })
	@PutMapping("/{idUser}")
	public ResponseUserDTO alteraUser(@RequestBody @Valid PostUserDTO dto, //
			@RequestParam("idUser") Long idUser) {

		verificaAutorizado(idUser);
		User usuarioMod = mapper.toUser(dto);
		usuarioMod.setId(idUser);
		return mapper.toDTO(service.alteraUser(usuarioMod));
	}

	@ApiOperation(value = "Deletar um usuário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuário deletado com sucesso") })
	@DeleteMapping("/{idUser}")
	public ServerResponse deleteById(@PathVariable("idUser") Long idUser) {
		verificaAutorizado(idUser);
		service.deleteById(idUser);
		return new ServerResponse(HttpStatus.OK, "Usuário excluído com sucesso");
	}

	@ApiOperation(value = "Envia um email de reset de senha ao Usuário")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Um email contendo as instruções foi enviado para a sua conta"), //
			@ApiResponse(code = 404, message = "Este email não possui uma conta vinculada") })
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping("/resetPassword")
	public ServerResponse resetaSenha(HttpServletRequest request, @RequestParam("email") String email) {
		User usuario = service.findByEmail(email);
		String token = UUID.randomUUID().toString();
		service.createPasswordResetTokenForUser(usuario, token);
		mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, usuario));
		return new ServerResponse(HttpStatus.OK, "Um email contendo as instruções foi enviado para a sua conta");
	}

	@ApiOperation(value = "Recebe a nova senha com o token e id para validação")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Senha alterada com Sucesso"), //
			@ApiResponse(code = 401, message = "Token inválido") })
	@PostMapping("/changePassword")
	public ServerResponse showChangePasswordPage(@RequestParam("id") Long id, //
			@RequestParam("token") String token, //
			@RequestBody @Valid PasswordDTO password) {
		String result = validatePasswordResetToken(id, token);
		if (result != null) {
			return new ServerResponse(HttpStatus.UNAUTHORIZED, "Token inválido");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		service.changeUserPassword(user, password.getSenha());
		return new ServerResponse(HttpStatus.OK, "Senha alterada com Sucesso");
	}

	private void verificaAutorizado(Long idUser) {
		/*
		 * Confere se é o mesmo usuário ou se é ADMIN. Caso não seja nenhum dos 2 ,
		 * retorna OperacaoNaoSuportadaException.
		 */
		User user = service.findById(idUser);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean isSameUser = authentication.getName().equals(user.getEmail());
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		if (!(isSameUser || isAdmin)) {
			throw new OperacaoNaoSuportadaException("Ação não autorizada");
		}
	}

	private SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user) {
		String url = "https://frontendcombacon.herokuapp.com/novasenha?id=" + user.getId() + "&token=" + token;
		String message = "Você está recebendo este email pois solicitou a recuperação do acesso a sua conta PetCodes,"
				+ " caso não tenha sido você, por favor ignore este email. \r\n Caso você tenha solicitado, basta"
				+ "acessar o link abaixo.";
		return constructEmail("Processo de recuperação de acesso PetCodes", message + " \r\n" + url, user);
	}

	private SimpleMailMessage constructEmail(String subject, String body, User user) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		email.setFrom("petcodes.batatacombacon@gmail.com.br");
		return email;
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	public String validatePasswordResetToken(long id, String token) {
		PasswordResetToken passToken = tokenRepository.findByToken(token);
		if ((passToken == null) || (passToken.getUsuario().getId() != id)) {
			return "invalidToken";
		}

		if (passToken.getDataExpira().isBefore(LocalDateTime.now())) {
			return "expired";
		}

		User user = passToken.getUsuario();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return null;
	}
}
