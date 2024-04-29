package com.AZDash2.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.AZDash2.entity.Issue;
import com.AZDash2.repository.IssueRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@SpringBootTest
public class IssueServiceUnitTest {
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
            "2024-04-11T18:03:33.444-0600",
            null,
            "Subir codigo a git de nuestro equipo",
            "Ignacio Soto",
            "2024-04-09T09:36:42.597-0600",
            null,
            "2.2.2",
            "Ignacio Soto",
            null,
            null,
            null,
            null)
        );


        HttpResponse<String> response = Mockito.mock(HttpResponse.class);
        given(httpClient.send(any(HttpRequest.class),(HttpResponse.BodyHandler<String>) any( HttpResponse.BodyHandler.class))).willReturn(response);
        given(response.statusCode()).willReturn(200);
        given(response.body()).willReturn("{\"expand\":\"names,schema\",\"startAt\":0,\"maxResults\":100,\"total\":1,\"issues\":[{\"expand\":\"operations,versionedRepresentations,editmeta,changelog,renderedFields\",\"id\":\"10119\",\"self\":\"https://adise.atlassian.net/rest/api/2/issue/10119\",\"key\":\"DAS-24\",\"fields\":{\"summary\":\"Subir codigo a git de nuestro equipo\",\"creator\":{\"self\":\"https://adise.atlassian.net/rest/api/2/user?accountId=619ffe08744c4d0069cb51c5\",\"accountId\":\"619ffe08744c4d0069cb51c5\",\"emailAddress\":\"a00756853@tec.mx\",\"avatarUrls\":{\"48x48\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"24x24\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"16x16\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"32x32\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\"},\"displayName\":\"Ignacio Soto\",\"active\":true,\"timeZone\":\"America/Mexico_City\",\"accountType\":\"atlassian\"},\"resolutiondate\":\"2024-04-11T18:03:33.444-0600\",\"created\":\"2024-04-09T09:36:42.597-0600\",\"customfield_10051\":\"2.2.2\",\"comment\":{\"comments\":[],\"self\":\"https://adise.atlassian.net/rest/api/2/issue/10119/comment\",\"maxResults\":0,\"total\":0,\"startAt\":0},\"assignee\":{\"self\":\"https://adise.atlassian.net/rest/api/2/user?accountId=619ffe08744c4d0069cb51c5\",\"accountId\":\"619ffe08744c4d0069cb51c5\",\"emailAddress\":\"a00756853@tec.mx\",\"avatarUrls\":{\"48x48\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"24x24\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"16x16\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\",\"32x32\":\"https://secure.gravatar.com/avatar/a1405c84e312d8e9627b944be24ca112?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FIS-0.png\"},\"displayName\":\"Ignacio Soto\",\"active\":true,\"timeZone\":\"America/Mexico_City\",\"accountType\":\"atlassian\"}}}]}");

        // WHEN
        List<Issue> issues = issueService.getIssues(projectIdOrKey, versionGiven);

        // Assert
        assertEquals(expectedIssues, issues);
    }
}
