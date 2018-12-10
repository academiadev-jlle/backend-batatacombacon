package br.com.academiadev.BatataComBaconSpring.endpoint;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
import br.com.academiadev.BatataComBaconSpring.dto.post.PostPetDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.ResponsePetDTO;
import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Porte;
import br.com.academiadev.BatataComBaconSpring.enums.Sexo;
import br.com.academiadev.BatataComBaconSpring.exception.OperacaoNaoSuportadaException;
import br.com.academiadev.BatataComBaconSpring.mapper.PetMapper;
import br.com.academiadev.BatataComBaconSpring.model.Pet;
import br.com.academiadev.BatataComBaconSpring.model.Usuario;
import br.com.academiadev.BatataComBaconSpring.service.PetService;
import br.com.academiadev.BatataComBaconSpring.service.UserService;
import br.com.academiadev.BatataComBaconSpring.service.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/")
@Api("Endpoint de Pet")
public class PetEndpoint {

	@Autowired
	private PetService petService;

	@Autowired
	private UserService userService;

	@Autowired
	private PetMapper mapper;

	@ApiOperation("Cria um Pet")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Pet criado com sucesso!"), //
			@ApiResponse(code = 400, message = "Não foi possível validar as informações"), //
	})
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("pet")
	public ResponsePetDTO criaPet(@RequestBody @Valid PostPetDTO dto) {
		verificaAutorizado(dto.getIdUsuario());
		Usuario usuario = userService.findById(dto.getIdUsuario());
		Pet pet = mapper.toPet(dto);
		pet.setUsuario(usuario);
		return mapper.toDTO(petService.save(pet));

	}

	/*
	 * Aqui eu recebo um PetDTO e o id de um Pet existente Uso estas informações
	 * para montar um pet e chamo o service solicitando a alteração
	 */
	@ApiOperation("Altera um Pet")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Pet alterado com sucesso"), //
			@ApiResponse(code = 404, message = "Pet não encontrado") //
	})
	@PutMapping("pet/{idPet}")
	public ResponsePetDTO alteraPet(@RequestBody @Valid PostPetDTO dto, @RequestParam("idPet") Long idPet) {
		verificaAutorizado(dto.getIdUsuario());
		Usuario usuario = userService.findById(dto.getIdUsuario());
		Pet pet = mapper.toPet(dto);
		pet.setId(idPet);
		pet.setUsuario(usuario);
		return mapper.toDTO(petService.alteraPet(pet));
	}

	/*
	 * Esta função pode receber OPCIONALMENTE varios itens que podem ser usados na
	 * filtragem. Eu os utilizo para montar um pet exemplo para o serviço de busca.
	 */
	@ApiOperation("Retorna a lista de pets, com possibilidade de filtragem")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Lista retornada com sucesso!") //
	})
	@GetMapping("pet")
	public Page<ResponsePetDTO> listaPets( //
			@RequestParam(required = false, defaultValue = "0") Integer page, //
			@RequestParam(required = false, defaultValue = "20") Integer size, //
			@RequestParam(required = false) Especie especie, //
			@RequestParam(required = false) Porte porte, //
			@RequestParam(required = false) Objetivo objetivo, //
			@RequestParam(required = false) Sexo sexo //
	) {
		Pageable pageable = PageRequest.of(page, size);
		Pet pet = Pet.builder().especie(especie).porte(porte).objetivo(objetivo).sexo(sexo).build();
		return Utils.toPageDTO(petService.findAll(Example.of(pet), pageable), mapper::toDTO);
	}

	@ApiOperation("Retorna a lista de Pets de um Usuário")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Lista retornada com sucesso") //
	})
	@GetMapping("user/{idUser}/pet")
	public Page<ResponsePetDTO> listaPetsDeUsuario(@PathVariable("idUser") Long idUser,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "20") Integer size,
			@RequestParam(required = false) Especie especie, //
			@RequestParam(required = false) Porte porte, //
			@RequestParam(required = false) Objetivo objetivo, //
			@RequestParam(required = false) Sexo sexo //
	) {
		verificaAutorizado(idUser);
		Pageable pageable = PageRequest.of(page, size);
		Usuario usuario = new Usuario(idUser);
		Pet pet = Pet.builder().especie(especie).porte(porte).objetivo(objetivo).sexo(sexo).usuario(usuario).build();
		return Utils.toPageDTO(petService.findAll(Example.of(pet), pageable), mapper::toDTO);
	}

	@ApiOperation("Retorna as informações de um pet")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Pet encontrado com sucesso") //
	})
	@GetMapping("pet/{idPet}")
	public ResponsePetDTO buscarPet(@PathVariable("idPet") Long idPet) {
		return mapper.toDTO(petService.findById(idPet));
	}

	@ApiOperation("Exclui um pet")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Pet excluido com sucesso") //
	})
	@DeleteMapping("pet/{idPet}")
	public ServerResponse deletarPet(@PathVariable("idPet") Long idPet) {
		verificaAutorizado(petService.findById(idPet).getUsuario().getId());
		petService.deleteById(idPet);
		return new ServerResponse(HttpStatus.OK, "Pet excluído com sucesso");
	}

	private void verificaAutorizado(Long idUser) {
		/*
		 * Confere se é o mesmo usuário ou se é ADMIN. Caso não seja nenhum dos 2 ,
		 * retorna OperacaoNaoSuportadaException.
		 */
		Usuario user = userService.findById(idUser);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean isSameUser = authentication.getName().equals(user.getEmail());
		boolean isAdmin = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		if (!(isSameUser || isAdmin)) {
			throw new OperacaoNaoSuportadaException("Ação não autorizada");
		}
	}
}
