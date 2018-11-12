package br.com.academiadev.BatataComBaconSpring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.BatataComBaconSpring.model.Pet;
import br.com.academiadev.BatataComBaconSpring.repository.PetRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/pet")
@Api("Serviços de Pet")
public class PetEndpoint {

	@Autowired
	private PetRepository petRepository;

	@ApiOperation(value = "Cria um Pet")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Pet criado com sucesso!") })
	@PostMapping
	@CrossOrigin//("https://frontendcombacon.herokuapp.com/")
	public void criaPet(@RequestBody Pet pet) {
		petRepository.save(pet);
	}

	@ApiOperation(value = "Retorna a lista de Pets")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Lista retornada com sucesso!") })
	@GetMapping
	@CrossOrigin//("https://frontendcombacon.herokuapp.com/")
	public List<Pet> listaPet() {
		return petRepository.findAll();
	}

	@ApiOperation(value = "Retorna um pet")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Pet encontrado com sucesso") })
	@GetMapping("/{id}")
	@CrossOrigin//("https://frontendcombacon.herokuapp.com/")
	public Pet buscarPor(@PathVariable Long id) {
		return petRepository.findById(id).orElse(null);
	}

	@ApiOperation(value = "Deleta um pet")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Pet deletado com sucesso") })
	@DeleteMapping("/{id}")
	@CrossOrigin//("https://frontendcombacon.herokuapp.com/")
	public void deletarPet(@PathVariable Long id) {
		petRepository.deleteById(id);
	}

}
