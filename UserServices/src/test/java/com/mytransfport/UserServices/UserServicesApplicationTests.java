package com.mytransfport.UserServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytransfport.UserServices.DTO.Create.CreateUsuarioDTO;
import com.mytransfport.UserServices.DTO.Update.UpdateUsuarioDTO;
import com.mytransfport.UserServices.Entity.Usuario;
import com.mytransfport.UserServices.Service.FirebaseService;
import jdk.vm.ci.meta.Local;
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
	/*
	Usuario usuario = new Usuario();
	private String emailTeste = Math.abs(Math.random()*100) + "@teste.busion";


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
		createUsuarioDTO.setEmail(emailTeste);
		createUsuarioDTO.setSenha("123456");
		createUsuarioDTO.setDataNascimento(LocalDateTime.now());

		mockMvc.perform(post("/UserServices")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(createUsuarioDTO)))
				.andExpect(status().isCreated());
		Thread.sleep(5000);
	}

	@Test
	void FirebaseCaseGetAll() throws Exception {
		MvcResult resultActions = this.mockMvc
				.perform(get("/UserServices/")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

	}
	@Test
	void FirebaseCaseGetOne() throws Exception {
		MvcResult resultActions = this.mockMvc
				.perform(get("/UserServices/2DPC1QjcCDfJ9dxsnTePg5TkkYZ2 ")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();
	}
	@Test
	void FirebaseCaseUpdate() throws Exception {
		FirebaseService fbs = new FirebaseService();
		CreateUsuarioDTO updateUsuarioDTO = new CreateUsuarioDTO();

		String uid = fbs.getUserUidByEmail(emailTeste);

		updateUsuarioDTO.setNome("Nome test Mudando");
		updateUsuarioDTO.setEmail("aaaa.axs@gmail.com");
		updateUsuarioDTO.setDataNascimento(LocalDateTime.now());
		updateUsuarioDTO.setSenha("111");

		ResultActions resultActions = this.mockMvc
				.perform(put("/UserServices/2DPC1QjcCDfJ9dxsnTePg5TkkYZ2")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(updateUsuarioDTO)))
				.andExpect(status().isOk());
	}

	@Test
	void FirebaseCaseDelete() throws Exception{
		FirebaseService fbs = new FirebaseService();
		String uid = fbs.getUserUidByEmail(emailTeste);
		ResultActions resultActions = this.mockMvc
				.perform(delete("/UserServices/deleteUser?uid=" + uid)
						.contentType("application/json"))
				.andExpect(status().isNoContent());
	}
	*/
}
