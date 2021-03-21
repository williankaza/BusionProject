package br.com.fiap.my.transport.onibus.api;

import br.com.fiap.my.transport.onibus.api.config.TestConfig;
import br.com.fiap.my.transport.onibus.api.dto.*;
import br.com.fiap.my.transport.onibus.api.service.LinhaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import java.util.Timer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class ApplicationTests {
	@LocalServerPort
	private int EXPECTED_PORT = 8081;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private LinhaService linhaService;

	@Autowired(required = false)
	private TestRestTemplate restTemplate;

	@AfterAll
	static void reset(){
		/*
		int counter = 0;
		while (true){
			counter++;
		}

		*/
	}

	private void timeout(){
		int counter = 0;
		while (true){
			counter++;
		}
	}

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
	void LinhaCaseGetAll() throws Exception {
		MvcResult resultActions = this.mockMvc
				.perform(get("/linha")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

	}
	@Test
	void LinhaCaseGetOne() throws Exception {
		MvcResult resultActions = this.mockMvc
				.perform(get("/linha/2")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void LinhaCaseDelete() throws Exception{
		ResultActions resultActions = this.mockMvc
				.perform(delete("/linha/1")
						.contentType("application/json"))
				.andExpect(status().isNoContent());
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
				.perform(put("/linha/1/onibus/4")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(dadosLinha)))
				.andExpect(status().isOk());
	}

	@Test
	void OnibusCaseGetAll() throws Exception {
		ResultActions resultActions = this.mockMvc
				.perform(get("/linha/1/onibus")
						.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void OnibusCaseGetOne() throws Exception {
		ResultActions resultActions = this.mockMvc
				.perform(get("/linha/1/onibus")
						.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void OnibusCaseDelete() throws Exception{
		ResultActions resultActions = this.mockMvc
				.perform(delete("/linha/2/onibus/5")
						.contentType("application/json"))
				.andExpect(status().isNoContent());
	}


	@Test
	void RotaCaseCreate() throws Exception {
		RotaCreateUpdateDTO rotaCreateUpdateDTO = new RotaCreateUpdateDTO();
		rotaCreateUpdateDTO.setAtivo(true);
		rotaCreateUpdateDTO.setLongitude("-23.583502");
		rotaCreateUpdateDTO.setLongitude("-46.607773");
		rotaCreateUpdateDTO.setOrdem(10);

		mockMvc.perform(post("/linha/2/rota")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(rotaCreateUpdateDTO)))
				.andExpect(status().isCreated());
	}

	@Test
	void RotaCaseUpdate() throws Exception {
		RotaCreateUpdateDTO rotaCreateUpdateDTO = new RotaCreateUpdateDTO();
		rotaCreateUpdateDTO.setAtivo(true);
		rotaCreateUpdateDTO.setLongitude("-00.000000");
		rotaCreateUpdateDTO.setLongitude("-00.000000");
		rotaCreateUpdateDTO.setOrdem(20);

		ResultActions resultActions = this.mockMvc
				.perform(put("/linha/2/onibus/2")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(rotaCreateUpdateDTO)))
				.andExpect(status().isOk());
	}

	@Test
	void RotaCaseGetAll() throws Exception {
		ResultActions resultActions = this.mockMvc
				.perform(get("/linha/2/rota")
						.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void RotaCaseGetOne() throws Exception {
		ResultActions resultActions = this.mockMvc
				.perform(get("/linha/2/rota/3")
						.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void RotaCaseDelete() throws Exception{
		ResultActions resultActions = this.mockMvc
				.perform(delete("/linha/2/rota/1")
						.contentType("application/json"))
				.andExpect(status().isNoContent());
	}


	@Test
	void PosicaoCaseCreate() throws Exception {
		PosicaoCreateUpdateDTO posicaoCreateUpdateDTO = new PosicaoCreateUpdateDTO();
		posicaoCreateUpdateDTO.setLatitude("-23.537828");
		posicaoCreateUpdateDTO.setLongitude("-46.632984");
		posicaoCreateUpdateDTO.setLotacaoAtual(99);

		mockMvc.perform(post("/linha/2/onibus/2/posicao")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(posicaoCreateUpdateDTO)))
				.andExpect(status().isCreated());
	}

	@Test
	void PosicaoCaseUpdate() throws Exception {
		PosicaoCreateUpdateDTO posicaoCreateUpdateDTO = new PosicaoCreateUpdateDTO();
		posicaoCreateUpdateDTO.setLatitude("-23.537828");
		posicaoCreateUpdateDTO.setLongitude("-46.632984");
		posicaoCreateUpdateDTO.setLotacaoAtual(99);

		ResultActions resultActions = this.mockMvc
				.perform(put("/linha/2/onibus/1/posicao/2")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(posicaoCreateUpdateDTO)))
				.andExpect(status().isOk());
	}

	@Test
	void PosicaoCaseGetAll() throws Exception {
		ResultActions resultActions = this.mockMvc
				.perform(get("/linha/2/onibus/1/posicao")
						.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void PosicaoCaseGetOne() throws Exception {
		ResultActions resultActions = this.mockMvc
				.perform(get("/linha/2/onibus/1/posicao/2")
						.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void PosicaoCaseDelete() throws Exception{
		ResultActions resultActions = this.mockMvc
				.perform(delete("/linha/2/onibus/1/posicao/1")
						.contentType("application/json"))
				.andExpect(status().isNoContent());
	}

}


