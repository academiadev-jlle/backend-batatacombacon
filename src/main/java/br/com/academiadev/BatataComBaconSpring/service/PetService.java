package br.com.academiadev.BatataComBaconSpring.service;

import java.util.ArrayList;

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
	
	@Autowired 
	private UserService userService;
	
	//Inicializando ArrayList do pet recém criado pra evitar NullPointerException
	public Pet save(Pet pet) {
		pet.setFotos(new ArrayList<Long>());
		return repository.save(pet);
	}
	
	public Pet alteraPet(Pet petModificado) {
		Pet pet = repository.findById(petModificado.getId()).orElseThrow(() -> new PetNaoEncontradoException("Pet não encontrado"));
		pet.setNome(petModificado.getNome());
		pet.setEspecie(petModificado.getEspecie());
		pet.setLocalPet(petModificado.getLocalPet());
		pet.setObjetivo(petModificado.getObjetivo());
		pet.setPorte(petModificado.getPorte());
		pet.setSexo(petModificado.getSexo());
		repository.flush();
		return pet;
	}
	
	public Page<Pet> findAll(Pet pet,Pageable pageable){
		return repository.findAll(Example.of(pet), pageable);
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
	
	public void flush() {
		repository.flush();
	}
}
