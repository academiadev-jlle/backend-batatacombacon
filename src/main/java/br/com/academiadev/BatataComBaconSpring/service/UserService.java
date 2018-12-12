package br.com.academiadev.BatataComBaconSpring.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.academiadev.BatataComBaconSpring.exception.UserNaoEncontradoException;
import br.com.academiadev.BatataComBaconSpring.model.PasswordResetToken;
import br.com.academiadev.BatataComBaconSpring.model.Usuario;
import br.com.academiadev.BatataComBaconSpring.repository.PasswordTokenRepository;
import br.com.academiadev.BatataComBaconSpring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordTokenRepository tokenRepository;

	public Usuario save(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		return repository.save(usuario);
	}
	
	public Usuario alteraUser(Usuario userModificado) {
		Usuario user = repository.findById(userModificado.getId()).orElseThrow(() -> new UserNaoEncontradoException("Usuário não encontrado"));
		BeanUtils.copyProperties(userModificado, user);
//		user.setNome(userModificado.getNome());
//		user.setEmail(userModificado.getEmail());
//		user.setSenha(new BCryptPasswordEncoder().encode(userModificado.getSenha()));
		return repository.save(user);
		
	}

	public Page<Usuario> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Usuario findById(Long idUser) {
		return repository.findById(idUser).orElseThrow(() -> new UserNaoEncontradoException("Usuário não encontrado"));
	}

	public void deleteById(Long idUser) {
		repository.delete(findById(idUser));
	}
	
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new UserNaoEncontradoException("Este email não possui uma conta vinculada"));
	}
	
	public void createPasswordResetTokenForUser(Usuario user, String token) {
	    PasswordResetToken myToken = new PasswordResetToken(token, user);
	    tokenRepository.save(myToken);
	}
	
	public void changeUserPassword(Usuario user, String password) {
	    user.setSenha(new BCryptPasswordEncoder().encode(password));
	    repository.save(user);
	}
	
}
