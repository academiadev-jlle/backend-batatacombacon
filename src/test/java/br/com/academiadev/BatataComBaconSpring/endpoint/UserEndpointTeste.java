package br.com.academiadev.BatataComBaconSpring.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.academiadev.BatataComBaconSpring.BatataComBaconSpringApplication;
import br.com.academiadev.BatataComBaconSpring.repository.UserRepository;

@ActiveProfiles("teste")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BatataComBaconSpringApplication.class)
@Ignore
public class UserEndpointTeste {
	
	 @Autowired
	    private MockMvc mvc;

	    @Value("${security.oauth2.client.client-id}")
	    private String CLIENT_ID;

	    @Value("${security.oauth2.client.client-secret}")
	    private String CLIENT_SECRET;

	    @Mock
	    private UserRepository repository;
	    
	    @Test
	    @Transactional
	    @Autowired
	    public void loginTest() throws Exception {
	        String token = getToken("admin@admin.com", "adminadmin");
	        Assertions.assertThat(token).isNotNull();
	    }
	    
	    private String getToken(String username, String password) throws Exception {

	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        params.add("grant_type", "password");
	        params.add("username", username);
	        params.add("password", password);

	        ResultActions result
	                = mvc.perform(post("/oauth/token")
	                .params(params)
	                .with(SecurityMockMvcRequestPostProcessors.httpBasic(CLIENT_ID, CLIENT_SECRET))
	                .accept("application/json;charset=UTF-8"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType("application/json;charset=UTF-8"));

	        String resultString = result.andReturn().getResponse().getContentAsString();

	        JacksonJsonParser jsonParser = new JacksonJsonParser();
	        return jsonParser.parseMap(resultString).get("access_token").toString();
	    }

}
