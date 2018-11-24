package br.com.academiadev.BatataComBaconSpring.endpoint;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.BatataComBaconSpring.enums.Especie;
import br.com.academiadev.BatataComBaconSpring.enums.Objetivo;
import br.com.academiadev.BatataComBaconSpring.enums.Porte;
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

	@ApiOperation("Pegar especies de pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de espécies retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "especies")
	public HashMap<String,String> getEspecies() {
		HashMap<String,String> especies = new HashMap<>();
		for (Especie especie : Especie.values()) {
			especies.put(especie.name(), especie.getDescricao());
		}
		return especies;
	}
	
	@ApiOperation("Pegar objetivos para pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de Objetivos retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "objetivos")
	public HashMap<String,String> getObjetivos() {
		HashMap<String,String> objetivos = new HashMap<>();
		for (Objetivo objetivo : Objetivo.values()) {
			objetivos.put(objetivo.name(), objetivo.getDescricao());
		}
		return objetivos;
	}
	
	@ApiOperation("Pegar portes para pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de portes retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "porte")
	public HashMap<String,String> getPorte() {
		HashMap<String,String> portes = new HashMap<>();
		for (Porte porte : Porte.values()) {
			portes.put(porte.name(), porte.getDescricao());
		}
		return portes;
	}
	
	@ApiOperation("Pegar sexos para pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de sexos retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "sexo")
	public HashMap<String,String> getSexo() {
		HashMap<String,String> sexos = new HashMap<>();
		for (Sexo sexo : Sexo.values()) {
			sexos.put(sexo.name(), sexo.getDescricao());
		}
		return sexos;
	}
}
