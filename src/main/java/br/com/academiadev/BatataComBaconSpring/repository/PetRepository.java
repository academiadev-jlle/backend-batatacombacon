package br.com.academiadev.BatataComBaconSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.academiadev.BatataComBaconSpring.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	
	List<Pet> findAllByUsuario_id(Long id);

}
