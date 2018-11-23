package br.com.academiadev.BatataComBaconSpring.endpoint;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.academiadev.BatataComBaconSpring.config.ExceptionResponse;
import br.com.academiadev.BatataComBaconSpring.dto.post.PostUserDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.RequestUserDTO;
import br.com.academiadev.BatataComBaconSpring.service.UserService;
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

	@ApiOperation(value = "Cria um usuario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuario criado com sucesso") })
	@PostMapping
	public RequestUserDTO save(@RequestBody @Valid PostUserDTO dto) {
		return service.save(dto);
	}

	@ApiOperation(value = "Retorna a lista de usuarios")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso") })
	@GetMapping
	public List<RequestUserDTO> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Retorna um usuário pelo Id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Usuário encontrado com sucesso")})
	@GetMapping("/{idUser}")
	public RequestUserDTO findById(@PathVariable("idUser") Long idUser) {
		return service.findById(idUser);
	}
	
	@ApiOperation(value = "Deletar um usuário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Usuário deletado com sucesso") })
	@DeleteMapping("/{idUser}")
	public ExceptionResponse deleteById(@PathVariable("idUser") Long idUser) {
		return service.deleteById(idUser);
	}

}
