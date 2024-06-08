package com.AZDash2.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ReleaseHistoricalControllerTest {

  @Autowired private MockMvc mockMvc;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @Test
  public void testGetIssuesByDateAndRelease_Existing() throws Exception {
    Long validIdRelease = 1L;

    mockMvc
        .perform(get("/historical/" + "2024-05-16" + "/" + 1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.release.id_release", is(validIdRelease.intValue())))
        .andExpect(jsonPath("$.recordDate", is("2024-05-16")));
  }

  @Test
  public void testGetIssuesByDateAndRelease_InvalidInput() throws Exception {
    mockMvc
        .perform(get("/historical/" + "fecha-invalida" + "/" + "idRelease-invalido"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetIssuesByDateAndRelease_NotFound() throws Exception {
    Long invalidIdRelease = 99L;

    mockMvc
        .perform(get("/historical/" + "2024-05-16" + "/" + invalidIdRelease))
        .andExpect(status().isNotFound());
  }
}
