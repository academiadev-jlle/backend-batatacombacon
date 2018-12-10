package br.com.academiadev.BatataComBaconSpring.endpoint;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import javax.transaction.Transactional;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.academiadev.BatataComBaconSpring.BatataComBaconSpringApplication;
import br.com.academiadev.BatataComBaconSpring.dto.post.PostUserDTO;
import br.com.academiadev.BatataComBaconSpring.repository.UserRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatataComBaconSpringApplication.class)
public class UserEndpointTeste {

	@Autowired
	private MockMvc mvc;

	@Value("${security.oauth2.client.client-id}")
	private String CLIENT_ID;

	@Value("${security.oauth2.client.client-secret}")
	private String CLIENT_SECRET;

	@Mock
	private UserRepository repository;

	private String usuarioEmail = "petcodes@petcodes.com.br";

	private String usuarioNome = "José Silva Figueiredo";

	private String usuarioSenha = "SuperSecreto";

	@Test
	@Transactional
	public void loginTest_Admin() throws Exception {
		String token = getToken("admin@batatacombacon.com.br", "AdminComBacon");
		Assert.assertFalse(token.equals(null));
	}

	@Test
	@Transactional
	public void loginTest_UsuarioInvalido() throws Exception {
		try {
			getToken("admin@batatacombacon.com.br", "SenhaErrada");
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser NullPointerException", e instanceof NullPointerException);
		}
	}

	@Test
	@Transactional
	public void loginTest_UsuarioInexistene() throws Exception {
		try {
			getToken("teste@teste.com.br", "SenhaTeste");
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			Assert.assertTrue("Deveria ser NullPointerException", e instanceof NullPointerException);
		}
	}

	@Test
	@Transactional
	public void loginTest_Usuario() throws Exception {
		criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		Assert.assertFalse(token.equals(null));
	}

	@Test
	@Transactional
	public void criaUsuario_Valido() throws Exception {
		criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
	}

	@Test
	@Transactional
	public void criaUsuario_Invalido() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		PostUserDTO user = new PostUserDTO();
		user.setEmail("petcodes");
		user.setNome(usuarioNome);
		user.setSenha(usuarioSenha);
		mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(mapper.writeValueAsString(user))).andExpect(status().isBadRequest());
	}

	@Test
	@Transactional
	public void getDetalhes_Usuario() throws Exception {
		Long id = criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		mvc.perform(get("/user/".concat(id.toString()))//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.nome", is(usuarioNome)))//
				.andExpect(jsonPath("$.email", is(usuarioEmail)));
	}

	@Test
	@Transactional
	public void getDetalhes_whoami() throws Exception {
		criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		mvc.perform(get("/user/whoami").header("Authorization", "Bearer " + token))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.nome", is(usuarioNome)))//
				.andExpect(jsonPath("$.email", is(usuarioEmail)));
	}

	@Test
	@Transactional
	public void getDetalhes_outroUsuario() throws Exception {
		criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		mvc.perform(get("/user/".concat("1"))//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isUnauthorized());
	}

	@Test
	@Transactional
	public void getDetalhes_UsuarioInexistente() throws Exception {
		criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		mvc.perform(get("/user/".concat("99"))//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	public void getListaUsarios_comoUsuario() throws Exception {
		criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		mvc.perform(get("/user")//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isForbidden());
		mvc.perform(get("/user/")//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isForbidden());
	}

	@Test
	@Transactional
	public void getListaUsarios_comoAdmin() throws Exception {
		String token = getToken("admin@batatacombacon.com.br", "AdminComBacon");
		mvc.perform(get("/user")//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.content.[0].email", is("admin@batatacombacon.com.br")));
		mvc.perform(get("/user/")//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.content.[0].email", is("admin@batatacombacon.com.br")));
	}

	@Test
	@Transactional
	public void deletaUsuario_Valido() throws Exception {
		Long id = criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		mvc.perform(delete("/user/".concat(id.toString()))//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isOk());//
		mvc.perform(get("/user/".concat(id.toString()))//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	public void deletaUsuario_Invalido() throws Exception {
		criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		mvc.perform(delete("/user/".concat("1"))//
				.header("Authorization", "Bearer " + token))//
				.andExpect(status().isUnauthorized());//
	}

	@Test
	@Transactional
	public void alteraUsuario_Valido() throws Exception {
		Long id = criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		PostUserDTO usuarioMod = new PostUserDTO();
		usuarioMod.setNome("Novo Nome");
		usuarioMod.setSenha("Nova Senha");
		usuarioMod.setEmail("novoemail@petcodes.com.br");
		mvc.perform(put("/user/".concat(id.toString())).param("idUser", id.toString())//
				.header("Authorization", "Bearer " + token)//
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
				.content(new ObjectMapper().writeValueAsString(usuarioMod)))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.nome", is("Novo Nome")))//
				.andExpect(jsonPath("$.email", is("novoemail@petcodes.com.br")));
	}

	@Test
	@Transactional
	public void alteraUsuario_OutroUsuario() throws Exception {
		criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		PostUserDTO usuarioMod = new PostUserDTO();
		usuarioMod.setNome("Novo Nome");
		usuarioMod.setSenha("Nova Senha");
		usuarioMod.setEmail("novoemail@petcodes.com.br");
		mvc.perform(put("/user/".concat("1")).param("idUser", "1")//
				.header("Authorization", "Bearer " + token)//
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
				.content(new ObjectMapper().writeValueAsString(usuarioMod)))//
				.andExpect(status().isUnauthorized());
	}

	@Test
	@Transactional
	public void alteraUsuario_Inexistente() throws Exception {
		criaUsuario(usuarioEmail, usuarioNome, usuarioSenha);
		String token = getToken(usuarioEmail, usuarioSenha);
		PostUserDTO usuarioMod = new PostUserDTO();
		usuarioMod.setNome("Novo Nome");
		usuarioMod.setSenha("Nova Senha");
		usuarioMod.setEmail("novoemail@petcodes.com.br");
		mvc.perform(put("/user/".concat("99")).param("idUser", "99")//
				.header("Authorization", "Bearer " + token)//
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
				.content(new ObjectMapper().writeValueAsString(usuarioMod)))//
				.andExpect(status().isNotFound());
	}

	private String getToken(String username, String password) throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("username", username);
		params.add("password", password);

		ResultActions result = mvc.perform(post("/oauth/token").params(params)
				.with(SecurityMockMvcRequestPostProcessors.httpBasic(CLIENT_ID, CLIENT_SECRET))
				.accept("application/json;charset=UTF-8"));
//				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
	}

	private Long criaUsuario(String email, String nome, String senha)
			throws Exception, IOException, JsonGenerationException, JsonMappingException {
		/*
		 * Cria um usuário genérico, retorna o id do usuário
		 */
		ObjectMapper mapper = new ObjectMapper();
		PostUserDTO user = new PostUserDTO();
		user.setEmail(email);
		user.setNome(nome);
		user.setSenha("SuperSecreto");
		ResultActions result = mvc
				.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(mapper.writeValueAsString(user)))
				.andExpect(status().isCreated()).andExpect(content().contentType("application/json;charset=UTF-8"));
		String resultString = result.andReturn().getResponse().getContentAsString();
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return Long.valueOf(jsonParser.parseMap(resultString).get("id").toString());
	}

}
