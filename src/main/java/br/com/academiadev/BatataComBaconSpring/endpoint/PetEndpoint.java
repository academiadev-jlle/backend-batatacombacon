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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.BatataComBaconSpring.config.ExceptionResponse;
import br.com.academiadev.BatataComBaconSpring.dto.post.PostPetDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.RequestPetDTO;
import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Porte;
import br.com.academiadev.BatataComBaconSpring.enums.Sexo;
import br.com.academiadev.BatataComBaconSpring.mapper.PetMapper;
import br.com.academiadev.BatataComBaconSpring.model.Pet;
import br.com.academiadev.BatataComBaconSpring.model.User;
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
@CrossOrigin
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
	public RequestPetDTO criaPet(@RequestBody @Valid PostPetDTO dto) {
		User usuario = userService.findById(dto.getIdUsuario());
		Pet pet = mapper.toPet(dto);
		pet.setUsuario(usuario);
		return mapper.toDTO(petService.save(pet));

	}
	
	@ApiOperation("Altera um Pet")
	@ApiResponses({ //
		@ApiResponse(code = 200 , message = "Pet alterado com sucesso"), //
		@ApiResponse(code = 404, message = "Pet não encontrado") //
	})
	@PutMapping("pet/{idPet}")
	public Pet alteraPet(@RequestBody @Valid PostPetDTO dto, @RequestParam("idPet") Long idPet) {
		User usuario = userService.findById(dto.getIdUsuario());
		Pet pet = mapper.toPet(dto);
		pet.setId(idPet);
		pet.setUsuario(usuario);
		return petService.alteraPet(pet);
	}
	
	@ApiOperation("Retorna a lista de pets, com possibilidade de filtragem")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Lista retornada com sucesso!") //
	})
	@GetMapping("pet")
	public Page<RequestPetDTO> listaPets(//
			@RequestParam(required = false, defaultValue = "0") Integer page, //
			@RequestParam(required = false, defaultValue = "20") Integer size, //
			@RequestParam(required = false) Especie especie, //
			@RequestParam(required = false) Porte porte, //
			@RequestParam(required = false) Objetivo objetivo, //
			@RequestParam(required = false) Sexo sexo //
	) {
		Pageable pageable = PageRequest.of(page, size);
		Pet pet = Pet.builder().especie(especie).porte(porte).objetivo(objetivo).sexo(sexo).build();
		return Utils.toPageDTO(petService.findAll(pet, pageable), mapper::toDTO);
	}

	@ApiOperation("Retorna a lista de Pets de um Usuário")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Lista retornada com sucesso") //
	})
	@GetMapping("usuario/{idUser}/pet")
	public Page<RequestPetDTO> listaPetsDeUsuario(@PathVariable("idUser") Long idUser,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "20") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return Utils.toPageDTO(petService.findAllFromUser(idUser, pageable), mapper::toDTO);
	}

	@ApiOperation("Retorna as informações de um pet")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Pet encontrado com sucesso") //
	})
	@GetMapping("pet/{idPet}")
	public RequestPetDTO buscarPet(@PathVariable("idPet") Long idPet) {
		return mapper.toDTO(petService.findById(idPet));
	}

	@ApiOperation("Exclui um pet")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Pet excluido com sucesso") //
	})
	@DeleteMapping("pet/{idPet}")
	public ExceptionResponse deletarPet(@PathVariable("idPet") Long idPet) {
		petService.deleteById(idPet);
		return new ExceptionResponse(HttpStatus.OK, "Pet excluído com sucesso");
	}
}
