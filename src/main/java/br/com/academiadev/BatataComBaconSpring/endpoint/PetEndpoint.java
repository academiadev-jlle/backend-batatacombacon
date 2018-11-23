package br.com.academiadev.BatataComBaconSpring.endpoint;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.BatataComBaconSpring.config.ExceptionResponse;
import br.com.academiadev.BatataComBaconSpring.dto.post.PostPetDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.RequestPetDTO;
import br.com.academiadev.BatataComBaconSpring.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/")
@Api("Endpoint de Pet")
public class PetEndpoint {

	@Autowired
	private PetService service;

	@ApiOperation("Cria um Pet")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Pet criado com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("pet")
	public RequestPetDTO criaPet(@RequestBody @Valid PostPetDTO dto) {
		return service.save(dto);
	}

	@ApiOperation("Retorna a lista completa de Pets")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Lista retornada com sucesso!") //
	})
	@GetMapping("pet")
	public List<RequestPetDTO> listaPets() {
		return service.findAll();
	}

	@ApiOperation("Retorna a lista de Pets de um Usuário")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Lista retornada com sucesso") //
	})
	@GetMapping("usuario/{idUser}/pet")
	public List<RequestPetDTO> listaPetsDeUsuario(@PathVariable("idUser") Long idUser) {
		return service.findAllFromUser(idUser);
	}

	@ApiOperation("Retorna as informações de um pet")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Pet encontrado com sucesso") //
	})
	@GetMapping("pet/{idPet}")
	public RequestPetDTO buscarPet(@PathVariable("idPet") Long idPet) {
		return service.findById(idPet);
	}

	@ApiOperation("Exclui um pet")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Pet excluido com sucesso") //
	})
	@DeleteMapping("pet/{idPet}")
	public ExceptionResponse deletarPet(@PathVariable("idPet") Long idPet) {
		return service.deleteById(idPet);
	}
}
