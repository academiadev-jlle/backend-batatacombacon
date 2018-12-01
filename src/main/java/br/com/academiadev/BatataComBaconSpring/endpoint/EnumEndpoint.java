package br.com.academiadev.BatataComBaconSpring.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
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
public class EnumEndpoint {

	@ApiOperation("Pegar especies de pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de espécies retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "especies")
	public List<HashMap<String, String>>  getEspecies() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		for (Especie especie : Especie.values()) {
			HashMap<String,String> especies = new HashMap<>();
			especies.put("especie", especie.name());
			especies.put("descricao", especie.getDescricao());
			list.add(especies);
		}
		return list;
	}
	
	@ApiOperation("Pegar objetivos para pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de Objetivos retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "objetivos")
	public List<HashMap<String, String>> getObjetivos() {
		List<HashMap<String, String>> list = new ArrayList<>();
		for (Objetivo objetivo : Objetivo.values()) {
			HashMap<String,String> objetivos = new HashMap<>();
			objetivos.put("objetivo", objetivo.name());
			objetivos.put("descricao", objetivo.getDescricao());
			list.add(objetivos);
		}
		return list;
	}
	
	@ApiOperation("Pegar portes para pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de portes retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "porte")
	public List<HashMap<String, String>> getPorte() {
		List<HashMap<String, String>> list = new ArrayList<>();
		for (Porte porte : Porte.values()) {
			HashMap<String,String> portes = new HashMap<>();
			portes.put("porte", porte.name());
			portes.put("descricao", porte.getDescricao());
			list.add(portes);
		}
		return list;
	}
	
	@ApiOperation("Pegar sexos para pets")
	@ApiResponses({ //
			@ApiResponse(code = 201, message = "Lista de sexos retornada com sucesso!") //
	})
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "sexo")
	public List<HashMap<String, String>> getSexo() {
		List<HashMap<String, String>> list = new ArrayList<>();
		for (Sexo sexo : Sexo.values()) {
			HashMap<String,String> sexos = new HashMap<>();
			sexos.put("sexo", sexo.name());
			sexos.put("descricao", sexo.getDescricao());
			list.add(sexos);
		}
		return list;
	}
}
