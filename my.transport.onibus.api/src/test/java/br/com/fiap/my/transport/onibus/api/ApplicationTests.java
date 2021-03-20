package br.com.fiap.my.transport.onibus.api;

import br.com.fiap.my.transport.onibus.api.config.TestConfig;
import br.com.fiap.my.transport.onibus.api.dto.LinhaCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.dto.LinhaDTO;
import br.com.fiap.my.transport.onibus.api.dto.OnibusCreateUpdateDTO;
import br.com.fiap.my.transport.onibus.api.service.LinhaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private LinhaService linhaService;

	@Autowired(required = false)
	private TestRestTemplate restTemplate;

	@Test
	void LinhaCaseCreate() throws Exception {
		LinhaCreateUpdateDTO linhaCreateUpdateDTO = new LinhaCreateUpdateDTO();
		linhaCreateUpdateDTO.setCodigoLinha("12345");
		linhaCreateUpdateDTO.setAtivo(true);
		MvcResult result = mockMvc.perform(post("/linha")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(linhaCreateUpdateDTO)))
				.andExpect(status().isCreated())
				.andReturn();
	}


	@Test
	void LinhaCaseUpdate() throws Exception {
		LinhaCreateUpdateDTO dadosLinha = new LinhaCreateUpdateDTO();
		dadosLinha.setCodigoLinha("999999");
		dadosLinha.setAtivo(false);

		ResultActions resultActions = this.mockMvc
				.perform(put("/linha/1")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(dadosLinha)))
				.andExpect(status().isOk());
	}

	@Test
	void LinhaCaseDelete() throws  Exception{
		ResultActions resultActions = this.mockMvc
				.perform(delete("/linha/1")
						.contentType("application/json"))
				.andExpect(status().isNoContent());
	}

	@Test
	void LinhaCaseGetAll() throws Exception {
		MvcResult resultActions = this.mockMvc
				.perform(get("/linha")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

	}


	@Test
	void OnibusCaseCreate() throws Exception {
		OnibusCreateUpdateDTO onibusCreateUpdateDTO = new OnibusCreateUpdateDTO();
		onibusCreateUpdateDTO.setAtivo(true);
		onibusCreateUpdateDTO.setCodigo("1234");
		mockMvc.perform(post("/linha/1/onibus")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(onibusCreateUpdateDTO)))
				.andExpect(status().isCreated());
	}

	@Test
	void OnibusCaseUpdate() throws Exception {
		LinhaCreateUpdateDTO dadosLinha = new LinhaCreateUpdateDTO();
		dadosLinha.setCodigoLinha("999999");
		dadosLinha.setAtivo(false);

		ResultActions resultActions = this.mockMvc
				.perform(put("/linha/1/onibus")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(dadosLinha)))
				.andExpect(status().isOk());
	}

	@Test
	void OnibusCaseDelete() throws  Exception{
		ResultActions resultActions = this.mockMvc
				.perform(delete("/linha/1")
						.contentType("application/json"))
				.andExpect(status().isNoContent());
	}

	@Test
	void OnibusCaseGetAll() throws Exception {
		ResultActions resultActions = this.mockMvc
				.perform(get("/linha")
						.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(result -> result.getClass().isArray());
	}

}


