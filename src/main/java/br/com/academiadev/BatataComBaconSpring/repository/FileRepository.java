package br.com.academiadev.BatataComBaconSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.academiadev.BatataComBaconSpring.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
	public File findByNome(String nome);

}
