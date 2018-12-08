package br.com.academiadev.BatataComBaconSpring.endpoint;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

import br.com.academiadev.BatataComBaconSpring.exception.OperacaoNaoSuportadaException;
import br.com.academiadev.BatataComBaconSpring.exception.UserNaoEncontradoException;
import br.com.academiadev.BatataComBaconSpring.mapper.UserMapper;
import br.com.academiadev.BatataComBaconSpring.model.Usuario;
import br.com.academiadev.BatataComBaconSpring.service.UserService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/login")
@Api("Endpoint de Login via Redes Sociais")
public class SocialLoginEndpoint {

	private String CLIENTID = "323333955272-7gksgqdpjdn210v8gj5eb3cldsikn5oo.apps.googleusercontent.com";

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper mapper;

	@Autowired
	private HttpTransport transport;

	@Autowired
	private JsonFactory jsonFactory;

	@PostMapping
	public String loginGoogle(@RequestBody String idTokenString) throws GeneralSecurityException, IOException {
		Usuario usuario = verificaToken(idTokenString);
		

	}

	public Usuario verificaToken(String idTokenString) throws GeneralSecurityException, IOException {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				// Specify the CLIENT_ID of the app that accesses the backend:
				.setAudience(Collections.singletonList(CLIENTID))
				// Or, if multiple clients access the backend:
				// .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		GoogleIdToken idToken = verifier.verify(idTokenString);
		if (idToken != null) {
			Payload payload = idToken.getPayload();

			// Print user identifier
//			String userId = payload.getSubject();
//			System.out.println("User ID: " + userId);

			// Get profile information from payload
			String email = payload.getEmail();
//			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String name = (String) payload.get("name");
//			String pictureUrl = (String) payload.get("picture");
//			String locale = (String) payload.get("locale");
//			String familyName = (String) payload.get("family_name");
//			String givenName = (String) payload.get("given_name");
			try {
				return userService.findByEmail(email);
			} catch (UserNaoEncontradoException ex) {
				Usuario usuario = new Usuario();
				usuario.setSenha(idTokenString);
				usuario.setEmail(email);
				usuario.setNome(name);
				usuario.setRole("ROLE_USER");
				return userService.save(usuario);
			}

		} else {
			throw new OperacaoNaoSuportadaException("Token inválido");
		}
	}
}
