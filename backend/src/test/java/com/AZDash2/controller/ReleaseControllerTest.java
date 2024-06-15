package com.AZDash2.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.AZDash2.entity.Release;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ReleaseControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void testGetReleaseById() throws Exception {

    Long id = 1L;
    Release expectedRelease = new Release();
    expectedRelease.setId_release(id);

    this.mockMvc
        .perform(get("/releases/" + id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id_release", is(id.intValue())));
  }

  @Test
  public void testGetReleaseNotFound() throws Exception {
    Long idInexistente = 999L; 

    this.mockMvc
        .perform(get("/releases/" + idInexistente))
        .andExpect(status().isNotFound()); 
  }
}
