/*package com.AZDash2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.AZDash2.entity.Issue;
import com.AZDash2.service.IssueService;

@ExtendWith(MockitoExtension.class)
public class IssueControllerTest {

    @Mock
    private IssueService issueService;

    @InjectMocks
    private IssueController issueController;

    private List<Issue> issues;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException, InterruptedException {
        issues = new ArrayList<>();

        new Issue(
            "DAS-24",
            "Done",
            "Subir codigo a git de nuestro equipo",
            "Ignacio Soto",
            false,
            "2024-04-09T09:36:42.597-0600",
            null,
            "Ignacio Soto",
            "No environemnts on issues",
            null,
            null,
            null,
            null
        );
    }

    @Test
    public void testPullIssues() throws URISyntaxException, IOException, InterruptedException {
        String projectIdOrKey = "DAS";
        String versionGiven = "2.2.2";
        
        when(issueService.getIssues(projectIdOrKey, versionGiven)).thenReturn(issues);

        ResponseEntity<List<Issue>> response = issueController.pullIssues(projectIdOrKey, versionGiven);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(issues, response.getBody());
        verify(issueService).getIssues(projectIdOrKey, versionGiven);
    }

    @Test
    public void testPullIssues_JiraApiFail() throws URISyntaxException, IOException, InterruptedException {
        String projectIdOrKey = "DAS";
        String versionGiven = "2.2.2";
        
        when(issueService.getIssues(projectIdOrKey, versionGiven)).thenThrow(new IOException());

        ResponseEntity<List<Issue>> response = issueController.pullIssues(projectIdOrKey, versionGiven);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(issueService).getIssues(projectIdOrKey, versionGiven);
    }
}
 */