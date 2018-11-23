package br.com.academiadev.BatataComBaconSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.academiadev.BatataComBaconSpring.config.ExceptionResponse;
import br.com.academiadev.BatataComBaconSpring.dto.post.PostPetDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.RequestPetDTO;
import br.com.academiadev.BatataComBaconSpring.exception.PetNaoEncontradoException;
import br.com.academiadev.BatataComBaconSpring.mapper.PetMapper;
import br.com.academiadev.BatataComBaconSpring.model.Pet;
import br.com.academiadev.BatataComBaconSpring.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	private PetRepository repository;
	
	@Autowired UserService userService;
	
	@Autowired
	private PetMapper mapper;
	
	public RequestPetDTO save(PostPetDTO dto) {
		return mapper.toDTO(repository.save(new Pet(dto)));
	}
	
	public List<RequestPetDTO> findAll(){
		return mapper.toDTO(repository.findAll());
	}
	
	public List<RequestPetDTO> findAllFromUser(Long idUser){
		userService.findById(idUser);
		return mapper.toDTO(repository.findAllByUsuario_id(idUser));
	}
	
	public RequestPetDTO findById(Long idPet) {
		return mapper.toDTO(repository.findById(idPet).orElseThrow(() -> new PetNaoEncontradoException("Pet não encontrado")));		
	}
	
	public ExceptionResponse deleteById(Long idPet) {
		repository.deleteById(findById(idPet).getId());
		return new ExceptionResponse(HttpStatus.OK, "Pet excluído com sucesso");
	}
}
