package br.com.academiadev.BatataComBaconSpring.endpoint;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.BatataComBaconSpring.enums.EnumInterface;
import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Porte;
import br.com.academiadev.BatataComBaconSpring.enums.Sexo;
import br.com.academiadev.BatataComBaconSpring.model.EnumResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/")
@Api("Endpoint de enuns pet")
public class EnumEndpoint {

	@ApiOperation("Pegar especies de pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de espécies retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "especies")
	public List<EnumResponse>  getEspecies() {
		return enumToList(Especie.values());
	}
	
	@ApiOperation("Pegar objetivos para pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de Objetivos retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "objetivos")
	public List<EnumResponse> getObjetivos() {
		return enumToList(Objetivo.values());
	}
	
	@ApiOperation("Pegar portes para pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de portes retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "porte")
	public List<EnumResponse> getPorte() {
		return enumToList(Porte.values());
	}
	
	@ApiOperation("Pegar sexos para pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de sexos retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "sexo")
	public List<EnumResponse> getSexo() {
		return enumToList(Sexo.values());
	}
	
	private List<EnumResponse> enumToList(EnumInterface[] values){
		return Stream.of(values).map(e -> new EnumResponse(e.name(), e.getDescricao())).collect(Collectors.toList());
	}
}
