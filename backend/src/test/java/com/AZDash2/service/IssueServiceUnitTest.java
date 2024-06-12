package com.AZDash2.service;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.AZDash2.entity.Issue;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.List;
import java.util.Map;

@SpringBootTest
class IssueServiceUnitTest {
    @Mock
    HttpClient httpClient;

    @InjectMocks
    IssueService issueService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(issueService, "jiraApiUrl", "https://localhost/rest/api/2/issue/");
        ReflectionTestUtils.setField(issueService, "jiraApiToken", "token");
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetIssues() throws URISyntaxException, IOException, InterruptedException {
      
         // GIVEN
        String projectIdOrKey = "issue";
        String versionGiven = "2.2.2";
        
        List<Issue> expectedIssues = List.of(
        
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
            ) 
        );


        HttpResponse<String> response = (HttpResponse<String>) Mockito.mock(HttpResponse.class);
        given(httpClient.send(any(HttpRequest.class),(HttpResponse.BodyHandler<String>) any( HttpResponse.BodyHandler.class))).willReturn(response);
        given(response.statusCode()).willReturn(200);
        given(response.body()).willReturn("{\"expand\":\"names,schema\",\"startAt\":0,\"maxResults\":100,\"total\":1,\"issues\":[{\"expand\":\"operations,versionedRepresentations,editmeta,changelog,renderedFields\",\"id\":\"10119\",\"self\":\"https://adise.atlassian.net/rest/api/2/issue/10119\",\"key\":\"DAS-24\",\"fields\":{\"summary\":\"Subir codigo a git de nuestro equipo\",\"creator\":{\"self\":\"https://adise.atlassian.net/rest/api/2/user?accountId=619ffe08744c4d0069cb51c5\",\"accountId\":\"619ffe08744c4d0069cb51c5\",\"emailAddress\":\"a00756853@tec.mx\",\"avatarUrls\":{\"48x48\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"24x24\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"16x16\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"32x32\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\"},\"displayName\":\"Ignacio Soto\",\"active\":true,\"timeZone\":\"America/Mexico_City\",\"accountType\":\"atlassian\"},\"created\":\"2024-04-09T09:36:42.597-0600\",\"resolutiondate\":\"2024-04-11T18:03:33.444-0600\",\"customfield_10051\":\"2.2.2\",\"comment\":{\"comments\":[],\"self\":\"https://adise.atlassian.net/rest/api/2/issue/10119/comment\",\"maxResults\":0,\"total\":0,\"startAt\":0},\"assignee\":{\"self\":\"https://adise.atlassian.net/rest/api/2/user?accountId=619ffe08744c4d0069cb51c5\",\"accountId\":\"619ffe08744c4d0069cb51c5\",\"emailAddress\":\"a00756853@tec.mx\",\"avatarUrls\":{\"48x48\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"24x24\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"16x16\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"32x32\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\"},\"displayName\":\"Ignacio Soto\",\"active\":true,\"timeZone\":\"America/Mexico_City\",\"accountType\":\"atlassian\"},\"status\":{\"self\":\"https://adise.atlassian.net/rest/api/2/status/10008\",\"description\":\"\",\"iconUrl\":\"https://adise.atlassian.net/\",\"name\":\"Done\",\"id\":\"10008\",\"statusCategory\":{\"self\":\"https://adise.atlassian.net/rest/api/2/statuscategory/3\",\"id\":3,\"key\":\"done\",\"colorName\":\"green\",\"name\":\"Done\"}}}}]}");

        // WHEN
        List<Issue> issues = issueService.getIssues(projectIdOrKey, versionGiven);

        // Assert
        assertEquals(expectedIssues, issues);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetBugs() throws URISyntaxException, IOException, InterruptedException {
      
         // GIVEN
        String projectIdOrKey = "issue";
        String versionGiven = "2.2.2";
        
        List<Issue> expectedIssues = List.of(
        
            new Issue(
            "DAS-67",
            "Done",
            "arreglar issue status",
            "Ignacio Soto",
            false,
            "2024-05-20T19:53:43.684-0600",
            null,
            "Marφa del Cielo Ramφrez Zavala",
            "Staging",
            null,
            null,
            null,
            null
            ) 
        );

        HttpResponse<String> response = (HttpResponse<String>) Mockito.mock(HttpResponse.class);
        given(httpClient.send(any(HttpRequest.class),(HttpResponse.BodyHandler<String>) any( HttpResponse.BodyHandler.class))).willReturn(response);
        given(response.statusCode()).willReturn(200);
        given(response.body()).willReturn("{\"expand\":\"names,schema\",\"startAt\":0,\"maxResults\":100,\"total\":1,\"issues\":[{\"expand\":\"operations,versionedRepresentations,editmeta,changelog,renderedFields\",\"id\":\"10162\",\"self\":\"https://adise.atlassian.net/rest/api/2/issue/10162\",\"key\":\"DAS-67\",\"fields\":{\"summary\":\"arreglar issue status\",\"creator\":{\"self\":\"https://adise.atlassian.net/rest/api/2/user?accountId=619ffe08744c4d0069cb51c5\",\"accountId\":\"619ffe08744c4d0069cb51c5\",\"emailAddress\":\"a00756853@tec.mx\",\"avatarUrls\":{\"48x48\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"24x24\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"16x16\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"32x32\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\"},\"displayName\":\"Ignacio Soto\",\"active\":true,\"timeZone\":\"America/Mexico_City\",\"accountType\":\"atlassian\"},\"resolutiondate\":\"2024-05-29T19:04:18.393-0600\",\"created\":\"2024-05-20T19:53:43.684-0600\",\"customfield_10053\":\"1.8\",\"comment\":{\"comments\":[],\"self\":\"https://adise.atlassian.net/rest/api/2/issue/10162/comment\",\"maxResults\":0,\"total\":0,\"startAt\":0},\"customfield_10055\":{\"self\":\"https://adise.atlassian.net/rest/api/2/customFieldOption/10028\",\"value\":\"Staging\",\"id\":\"10028\"},\"assignee\":{\"self\":\"https://adise.atlassian.net/rest/api/2/user?accountId=712020%3Af34607d5-55fe-4b71-bd76-10d66337540c\",\"accountId\":\"712020:f34607d5-55fe-4b71-bd76-10d66337540c\",\"emailAddress\":\"a01562798@tec.mx\",\"avatarUrls\":{\"48x48\":\"https://secure.gravatar.com/avatar/daf7fc977ada9533959c625d15917664?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FMZ-0.png\",\"24x24\":\"https://secure.gravatar.com/avatar/daf7fc977ada9533959c625d15917664?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FMZ-0.png\",\"16x16\":\"https://secure.gravatar.com/avatar/daf7fc977ada9533959c625d15917664?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FMZ-0.png\",\"32x32\":\"https://secure.gravatar.com/avatar/daf7fc977ada9533959c625d15917664?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FMZ-0.png\"},\"displayName\":\"Marφa del Cielo Ramφrez Zavala\",\"active\":true,\"timeZone\":\"America/Mexico_City\",\"accountType\":\"atlassian\"},\"status\":{\"self\":\"https://adise.atlassian.net/rest/api/2/status/10008\",\"description\":\"\",\"iconUrl\":\"https://adise.atlassian.net/\",\"name\":\"Done\",\"id\":\"10008\",\"statusCategory\":{\"self\":\"https://adise.atlassian.net/rest/api/2/statuscategory/3\",\"id\":3,\"key\":\"done\",\"colorName\":\"green\",\"name\":\"Done\"}}}}]}");

        // WHEN
        List<Issue> issues = issueService.getBugs(projectIdOrKey, versionGiven);

        // Assert
        assertEquals(expectedIssues, issues);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetTicketAmount() throws URISyntaxException, IOException, InterruptedException {

        // GIVEN
        String projectIdOrKey = "DAS";

        Map<String, Long> expectedAmount = Map.of("bugs", 2L, "issues", 2L);

        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        given(httpClient.send(any(HttpRequest.class),
                (HttpResponse.BodyHandler<String>) any(HttpResponse.BodyHandler.class))).willReturn(response);
        given(response.statusCode()).willReturn(200);
        given(response.body()).willReturn("{\"startAt\":0,\"maxResults\":0,\"total\":2,\"issues\":[]}");

        HttpResponse<String> responseIssue = Mockito.mock(HttpResponse.class);
        given(httpClient.send(any(HttpRequest.class),
                (HttpResponse.BodyHandler<String>) any(HttpResponse.BodyHandler.class))).willReturn(response);
        given(responseIssue.statusCode()).willReturn(200);
        given(responseIssue.body()).willReturn("{\"startAt\":0,\"maxResults\":0,\"total\":44,\"issues\":[]}");

        // WHEN
        Map<String, Long> ticketAmount = issueService.getTicketAmount(projectIdOrKey);
        
        // THEN
        assertEquals(expectedAmount, ticketAmount);
    }
    
}