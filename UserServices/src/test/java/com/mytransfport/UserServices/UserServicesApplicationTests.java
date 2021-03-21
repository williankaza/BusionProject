package com.mytransfport.UserServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytransfport.UserServices.DTO.Create.CreateUsuarioDTO;
import com.mytransfport.UserServices.DTO.Update.UpdateUsuarioDTO;
import com.mytransfport.UserServices.Entity.Usuario;
import com.mytransfport.UserServices.Service.FirebaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class UserServicesApplicationTests {

	Usuario usuario = new Usuario();



	@LocalServerPort
	private int EXPECTED_PORT = 8090;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@MockBean
	private FirebaseService firebaseService;

	@Test
	void FirebaseCaseCreate() throws Exception {
		CreateUsuarioDTO createUsuarioDTO = new CreateUsuarioDTO();
		createUsuarioDTO.setNome("Teste JUNIT Create");
		createUsuarioDTO.setEmail("junit@junit.com.br");
		createUsuarioDTO.setSenha("123456");
		createUsuarioDTO.setDataNascimento(LocalDateTime.now());
		mockMvc.perform(post("/UserServices/createUser")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(createUsuarioDTO)))
				.andExpect(status().isCreated());
		Thread.sleep(5000);
	}

	@Test
	void FirebaseCaseGetAll() throws Exception {
		MvcResult resultActions = this.mockMvc
				.perform(get("/UserServices/getAllUsers")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

	}
	@Test
	void FirebaseCaseGetOne() throws Exception {
		MvcResult resultActions = this.mockMvc
				.perform(get("/UserServices/ze8YKZc2ZgQk3UMsgxttzHfF15A2")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();
	}
	@Test
	void FirebaseCaseUpdate() throws Exception {
		UpdateUsuarioDTO updateUsuarioDTO = new UpdateUsuarioDTO();

		updateUsuarioDTO.setUid("ze8YKZc2ZgQk3UMsgxttzHfF15A2");
		updateUsuarioDTO.setNome("Nome test JUNIT");
		updateUsuarioDTO.setEmail("busion@junit.com");

		ResultActions resultActions = this.mockMvc
				.perform(post("/UserServices/editUser")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(updateUsuarioDTO)))
				.andExpect(status().isOk());
	}

	@Test
	void FirebaseCaseDelete() throws Exception{
		FirebaseService fbs = new FirebaseService();
		String uid = fbs.getUserUidByEmail("junit@junit.com.br");
		ResultActions resultActions = this.mockMvc
				.perform(delete("/UserServices/deleteUser?uid=" + uid)
						.contentType("application/json"))
				.andExpect(status().isNoContent());
	}

}
