package br.com.academiadev.BatataComBaconSpring.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Sexo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/")
@Api("Endpoint de enuns pet")
@CrossOrigin
public class EnumEndpoint {

	@ApiOperation("Pegar especies")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de espécies retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "especies")
	public Especie[] getEspecies() {
		return Especie.values();
	}
	
	@ApiOperation("Pegar objetivo")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de Objetivos retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "objetivos")
	public Objetivo[] getObjetivos() {
		return Objetivo.values();
	}
	
	@ApiOperation("Pegar porte animal")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de porte retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "porte")
	public Objetivo[] getPorte() {
		return Objetivo.values();
	}
	
	@ApiOperation("Pegar sexo")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de sexos retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "sexo")
	public Sexo[] getSexo() {
		return Sexo.values();
	}
}
