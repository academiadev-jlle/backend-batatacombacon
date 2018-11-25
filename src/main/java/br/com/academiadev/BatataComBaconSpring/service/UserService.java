package br.com.academiadev.BatataComBaconSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.academiadev.BatataComBaconSpring.exception.UserNaoEncontradoException;
import br.com.academiadev.BatataComBaconSpring.model.User;
import br.com.academiadev.BatataComBaconSpring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public User save(User usuario) {
		return repository.save(usuario);
	}
	
	public User alteraUser(User userModificado) {
		User user = repository.findById(userModificado.getId()).orElseThrow(() -> new UserNaoEncontradoException("Usuário não encontrado"));
		user.setNome(userModificado.getNome());
		user.setEmail(userModificado.getEmail());
		user.setSenha(userModificado.getSenha());
		repository.flush();
		return user;
	}

	public Page<User> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public User findById(Long idUser) {
		return repository.findById(idUser).orElseThrow(() -> new UserNaoEncontradoException("Usuário não encontrado"));
	}

	public void deleteById(Long idUser) {
		repository.delete(findById(idUser));
	}
}
