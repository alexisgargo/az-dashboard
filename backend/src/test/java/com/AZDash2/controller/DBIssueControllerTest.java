package com.AZDash2.controller;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class DBIssueControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @Test
  public void testCountLatestIssuesByDateAndRelease_Existing() throws Exception {
    Date validDate = new Date(dateFormat.parse("2024-05-16").getTime());
    Long validIdRelease = 1L;

    this.mockMvc
        .perform(get("/issues/count/" + validDate + "/" + validIdRelease))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", not(empty())));
  }

  @Test
  public void testCountLatestIssuesByDateAndRelease_NullDate() throws Exception {
    Long validIdRelease = 1L;
    this.mockMvc
        .perform(get("/issues/count/null/" + validIdRelease))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testCountLatestIssuesByDateAndRelease_NullIdRelease() throws Exception {
    Date validDate = new Date(dateFormat.parse("2024-05-16").getTime());
    this.mockMvc
        .perform(get("/issues/count/" + validDate + "/null"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testCountLatestIssuesByDateAndRelease_NoIssuesFound() throws Exception {
    Date validDate = new Date(dateFormat.parse("2024-05-16").getTime());
    Long validIdRelease = 99L;
    this.mockMvc
        .perform(get("/issues/count/" + validDate + "/" + validIdRelease))
        .andExpect(status().isNoContent());
  }

  @Test
  public void testCountLatestIssuesByDateAndRelease_InvalidData() throws Exception {
    this.mockMvc
        .perform(get("/issues/count/invalid-date/invalid-idRelease"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetOnlyBugByDateAndRelease_Existing() throws Exception {
    Date validDate = new Date(dateFormat.parse("2024-05-17").getTime());
    Long validIdRelease = 1L;

    this.mockMvc
        .perform(get("/OnlyBugs/" + validDate + "/" + validIdRelease))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", not(empty())));
  }

  @Test
  public void testGetOnlyBugByDateAndRelease_NullDate() throws Exception {
    Long validIdRelease = 1L;
    this.mockMvc
        .perform(get("/OnlyBugs/null/" + validIdRelease))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetOnlyBugByDateAndRelease_NullIdRelease() throws Exception {
    Date validDate = new Date(dateFormat.parse("2024-05-16").getTime());
    this.mockMvc
        .perform(get("/OnlyBugs/" + validDate + "/null"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetOnlyBugByDateAndRelease_InvalidData() throws Exception {
    this.mockMvc
        .perform(get("/OnlyBugs/invalid-date/invalid-idRelease"))
        .andExpect(status().isBadRequest());
  }
  @Test
  public void testGetOnlyIssuesByDateAndRelease_Existing() throws Exception {
    Date validDate = new Date(dateFormat.parse("2024-05-16").getTime());
    Long validIdRelease = 1L;

    this.mockMvc
        .perform(get("/OnlyIssues/" + validDate + "/" + validIdRelease))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", not(empty())));
  }

  @Test
  public void testGetOnlyIssuesByDateAndRelease_NullDate() throws Exception {
    Long validIdRelease = 1L;
    this.mockMvc
        .perform(get("/OnlyIssues/null/" + validIdRelease))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetOnlyIssuesByDateAndRelease_NullIdRelease() throws Exception {
    Date validDate = new Date(dateFormat.parse("2024-05-16").getTime());
    this.mockMvc
        .perform(get("/OnlyIssues/" + validDate + "/null"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetOnlyIssuesByDateAndRelease_InvalidData() throws Exception {
    this.mockMvc
        .perform(get("/OnlyIssues/invalid-date/invalid-idRelease"))
        .andExpect(status().isBadRequest());
  }

}
