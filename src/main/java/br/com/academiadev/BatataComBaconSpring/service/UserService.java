package br.com.academiadev.BatataComBaconSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.academiadev.BatataComBaconSpring.config.ExceptionResponse;
import br.com.academiadev.BatataComBaconSpring.dto.post.PostUserDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.RequestUserDTO;
import br.com.academiadev.BatataComBaconSpring.exception.UserNaoEncontradoException;
import br.com.academiadev.BatataComBaconSpring.mapper.UserMapper;
import br.com.academiadev.BatataComBaconSpring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserMapper mapper;
	
	public RequestUserDTO save(PostUserDTO dto) {
		return mapper.toDTO(repository.save(mapper.toUser(dto)));
	}
	
	public List<RequestUserDTO> findAll(){
		return mapper.toDTO(repository.findAll());
	}
	
	public RequestUserDTO findById(Long idUser) {
		return mapper.toDTO(repository.findById(idUser).orElseThrow(() -> new UserNaoEncontradoException("Usuário não encontrado")));
	}
	
	public ExceptionResponse deleteById(Long idUser) {
		findById(idUser);
		return new ExceptionResponse(HttpStatus.OK, "Usuário excluído com sucesso");
	}

}
