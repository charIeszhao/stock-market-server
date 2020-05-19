package com.demo.stockmarket.user;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
				.andExpect(status().isOk()).andReturn();

		when(service.getUserByEmail("admin@stockmarket.com")).thenReturn(newUser);
	}

	@Test
	public void getUsers() throws Exception {
		User newUser1 = new User();
		newUser1.setEmail("test1@stockmarket.com");
		newUser1.setPassword("test");
		newUser1.setRole("ADMIN");
		newUser1.setAddress("test address");
		newUser1.setFirstName("First1");
		newUser1.setLastName("Last1");
		newUser1.setActive(0);
		
		User newUser2 = new User();
		newUser2.setEmail("test2@stockmarket.com");
		newUser2.setPassword("test");
		newUser2.setRole("USER");
		newUser2.setAddress("test address");
		newUser2.setFirstName("First2");
		newUser2.setLastName("Last2");
		newUser2.setActive(0);
		
		String json1 = om.writeValueAsString(newUser1);
		String json2 = om.writeValueAsString(newUser2);
		
		mockMvc.perform(post("/user").content(json1).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk()).andReturn();
		
		mockMvc.perform(post("/user").content(json2).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk()).andReturn();
		
		mockMvc.perform(get("/user/all")
				.header("user.email", "test1@stockmarket.com")
				.header("user.role", "ADMIN")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

}
