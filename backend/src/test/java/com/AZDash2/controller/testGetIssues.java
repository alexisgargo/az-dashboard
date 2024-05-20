package com.AZDash2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.AZDash2.controller.DBIssueController;

import com.AZDash2.entity.Issue;
import com.AZDash2.service.DBIssueService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class testGetIssues {
    private MockMvc mockMvc;



    @Mock
    private DBIssueService issueService;

    @InjectMocks
    private DBIssueController issueController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
    }

    @Test
    public void testGetIssuesList() throws Exception {
        Issue issue1 = new Issue(
            "ISSUE-1", "Open", "Issue 1 summary", "User1", true, "2023-05-19", 
            "Update 1", "Assignee1", "Production", null, 
            Date.valueOf("2023-05-19"), Time.valueOf("12:00:00"), null);
        
        Issue issue2 = new Issue(
            "ISSUE-2", "Closed", "Issue 2 summary", "User2", false, "2023-05-20", 
            "Update 2", "Assignee2", "Staging", null, 
            Date.valueOf("2023-05-20"), Time.valueOf("13:00:00"), null);

            List<Issue> issues = Arrays.asList(issue1, issue2);

        when(issueService.getIssues()).thenReturn(issues);

        MvcResult mvcResult = mockMvc.perform(get("/az_dashboard/issues"))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Issue> returnedIssues = objectMapper.readValue(jsonResponse, new TypeReference<List<Issue>>() {});

        assertEquals(issues.size(), returnedIssues.size());
        assertEquals(issue1.getIssue_number(), returnedIssues.get(0).getIssue_number());
        assertEquals(issue1.getIssue_status(), returnedIssues.get(0).getIssue_status());
        assertEquals(issue1.getIssue_summary(), returnedIssues.get(0).getIssue_summary());
        assertEquals(issue2.getIssue_number(), returnedIssues.get(1).getIssue_number());
        assertEquals(issue2.getIssue_status(), returnedIssues.get(1).getIssue_status());
        assertEquals(issue2.getIssue_summary(), returnedIssues.get(1).getIssue_summary());
    }
}
