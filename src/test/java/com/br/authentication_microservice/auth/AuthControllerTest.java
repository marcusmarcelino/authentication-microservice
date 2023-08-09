package com.br.authentication_microservice.auth;

import java.net.URI;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenThereIsAnAuthenticationAttemptButTheUserIsNotRegistered() throws Exception {
    URI uri = new URI("/api/v1/auth/authenticate");
    JSONObject jsonObj = new JSONObject();
    jsonObj.put("email", "marcus@gmail.com");
    jsonObj.put("password", "password");
    String json = jsonObj.toString();
    mockMvc
        .perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }
}
