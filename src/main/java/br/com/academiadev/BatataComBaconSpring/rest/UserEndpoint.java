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

import br.com.academiadev.BatataComBaconSpring.model.User;
import br.com.academiadev.BatataComBaconSpring.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/user")
@Api("serviços de usuário")
public class UserEndpoint {

	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value = "Cria um usuario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuario criado com sucesso") })
	@PostMapping
	@CrossOrigin//("https://frontendcombacon.herokuapp.com/")
	public void novoUsuario(@RequestBody User user) {
		userRepository.save(user);
	}

	@ApiOperation(value = "Retorna a lista de usuarios")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Lista retornada com sucesso") })
	@GetMapping
	@CrossOrigin//("https://frontendcombacon.herokuapp.com/")
	public List<User> listarUsuario() {
		return userRepository.findAll();
	}
	
	@ApiOperation(value = "Retorna um usuário")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Usuário encontrado com sucesso")})
	@GetMapping("/{id}")
	@CrossOrigin//("https://frontendcombacon.herokuapp.com/")
	public User encontrarUsuarioId(@PathVariable Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	@ApiOperation(value = "Deletar um usuário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuário deletado com sucesso") })
	@DeleteMapping("/{id}")
	@CrossOrigin//("https://frontendcombacon.herokuapp.com/")
	public void deletarUsuarioID(@PathVariable Long id) {
		userRepository.deleteById(id);
	}

}
