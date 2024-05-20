package com.AZDash2.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.AZDash2.service.IssueService;

@ExtendWith(MockitoExtension.class)
public class IssueControllerTest {

    @Test
    void testPullTicketAmount() throws Exception {
        // Mock the IssueService
        IssueService issueService = mock(IssueService.class);
        
        // Create a sample response
        Map<String, Long> response = new HashMap<>();
        response.put("bugs", 2L);
        response.put("issues", 25L);
        
        // Mock the service method
        when(issueService.getTicketAmount("projectIdOrKey")).thenReturn(response);
        
        // Create an instance of the controller
        IssueController issueController = new IssueController();
        issueController.issueService = issueService;
        
        // Call the method under test
        ResponseEntity<Map<String, Long>> result = issueController.pullTicketAmount("projectIdOrKey");
        
        // Assert the response
        assert result.getStatusCode() == HttpStatus.OK;
        assert result.getBody().equals(response);
    }

}