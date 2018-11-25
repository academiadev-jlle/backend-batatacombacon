package br.com.academiadev.BatataComBaconSpring.endpoint;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.BatataComBaconSpring.config.ExceptionResponse;
import br.com.academiadev.BatataComBaconSpring.dto.post.PostUserDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.RequestUserDTO;
import br.com.academiadev.BatataComBaconSpring.mapper.UserMapper;
import br.com.academiadev.BatataComBaconSpring.service.UserService;
import br.com.academiadev.BatataComBaconSpring.service.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api("Endpoint de usuário")
@CrossOrigin
public class UserEndpoint {

	@Autowired
	private UserService service;

	@Autowired
	private UserMapper mapper;

	@ApiOperation(value = "Cria um usuario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuario criado com sucesso") })
	@PostMapping
	public RequestUserDTO save(@RequestBody @Valid PostUserDTO dto) {
		return mapper.toDTO(service.save(mapper.toUser(dto)));
	}

	@ApiOperation(value = "Retorna a lista de usuarios")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso") })
	@GetMapping
	public Page<RequestUserDTO> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "20") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return Utils.toPageDTO(service.findAll(pageable), mapper::toDTO);
	}

	@ApiOperation(value = "Retorna um usuário pelo Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Usuário encontrado com sucesso") })
	@GetMapping("/{idUser}")
	public RequestUserDTO findById(@PathVariable("idUser") Long idUser) {
		return mapper.toDTO(service.findById(idUser));
	}

	@ApiOperation(value = "Deletar um usuário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuário deletado com sucesso") })
	@DeleteMapping("/{idUser}")
	public ExceptionResponse deleteById(@PathVariable("idUser") Long idUser) {
		service.deleteById(idUser);
		return new ExceptionResponse(HttpStatus.OK, "Usuário excluído com sucesso");
	}

}
