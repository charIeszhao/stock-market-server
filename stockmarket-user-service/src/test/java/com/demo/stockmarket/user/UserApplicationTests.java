package com.demo.stockmarket.user;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.mapping.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;

import com.demo.stockmarket.entity.User;
import com.demo.stockmarket.user.controller.UserController;
import com.demo.stockmarket.user.filter.CORSFilter;
import com.demo.stockmarket.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@EntityScan("com.demo.stockmarket.entity")
public class UserApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private UserController userController;

	@MockBean
	private UserService service;

	private ObjectMapper om = new ObjectMapper();

//	@Test
//	public void contextLoads() {
//		assertThat(controller).isNotNull();
//	}

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).addFilters(new CORSFilter()).build();
	}

	@Test
	public void createUser() throws Exception {
		User newUser = new User();
		newUser.setEmail("charles@stockmarket.com");
		newUser.setPassword("test");
		newUser.setRole("USER");
		newUser.setAddress("test address");
		newUser.setFirstName("First");
		newUser.setLastName("Last");
		newUser.setActive(0);

		String json = om.writeValueAsString(newUser);

		MvcResult result = mockMvc.perform(post("/user").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();

		when(service.getUserByEmail("admin@stockmarket.com")).thenReturn(newUser);
	}

	@Test
	public void getUsers() throws Exception {
		MvcResult result = mockMvc.perform(
				get("/user").param("page", "0").param("pageSize", "5").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		
		
	}

}
