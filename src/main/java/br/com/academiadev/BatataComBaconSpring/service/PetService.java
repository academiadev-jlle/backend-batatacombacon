package br.com.academiadev.BatataComBaconSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.academiadev.BatataComBaconSpring.exception.PetNaoEncontradoException;
import br.com.academiadev.BatataComBaconSpring.model.Pet;
import br.com.academiadev.BatataComBaconSpring.model.User;
import br.com.academiadev.BatataComBaconSpring.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	private PetRepository repository;
	
	@Autowired UserService userService;
	
	public Pet save(Pet pet) {
		return repository.save(pet);
	}
	
	public Page<Pet> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Page<Pet> findAllFromUser(Long idUser, Pageable pageable){
		User usuario = userService.findById(idUser);
		Pet pet = new Pet();
		pet.setUsuario(usuario);
		return repository.findAll(Example.of(pet), pageable);
	}
	
	public Pet findById(Long idPet) {
		return repository.findById(idPet).orElseThrow(() -> new PetNaoEncontradoException("Pet não encontrado"));		
	}
	
	public void deleteById(Long idPet) {
		repository.delete(findById(idPet));
	}
}
