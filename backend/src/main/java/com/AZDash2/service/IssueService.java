package com.AZDash2.service;
import com.AZDash2.valueobject.Issue;
import com.AZDash2.valueobject.TeamProgress;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class IssueService {
    Logger logger = LoggerFactory.getLogger(IssueService.class);
    
    @Value("${jira.api.url}")
    private String jiraApiUrl;

    @Value("${jira.api.token}")
    private String jiraApiToken;

    public Issue getIssue(final String key) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(jiraApiUrl + "/rest/api/2/issue/" + key))
            .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
            .GET()
            .build();

        HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());

        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject issueJson = JsonParser.parseString(response.body())
    .getAsJsonObject();
        JsonObject fieldsJson = issueJson.getAsJsonObject("fields");
        JsonObject statusJson = fieldsJson.getAsJsonObject("status");

        Issue issue = new Issue();
        issue.setKey(issueJson.get("key").getAsString());
        issue.setStatus(statusJson.get("name").getAsString());
        issue.setDescription(fieldsJson.get("summary").getAsString());

        return issue;
    }


    public List<Issue> getAllIssues(final String projectIdOrKey) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder() ///rest/api/2/search?jql=project=DAS&maxResults=100&fields=id,summary,assignee
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=project=" + projectIdOrKey + "&maxResults=100&fields=id,summary,assignee"))  //aqui se le especifica cuanta info de dicho proyecto queremos traer
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
        .GET()
        .build();

        HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject issueJson = JsonParser.parseString(response.body()).getAsJsonObject();

        JsonArray allIssues = issueJson.getAsJsonArray("issues");
        
        List<Issue> issues = new ArrayList<>();

        for (JsonElement issueElement : allIssues) {
            Issue issue = new Issue();
            JsonObject issueObject = issueElement.getAsJsonObject();
            String key = issueObject.get("key").getAsString();
            JsonObject fieldsObject = issueElement.getAsJsonObject().getAsJsonObject("fields");
            String summary = fieldsObject.get("summary").getAsString();
            //logger.debug("ASSIGNEE {}", fieldsObject.get("assignee").toString().equals("null"));
            if (!fieldsObject.get("assignee").toString().equals("null")) {
                JsonObject assigneeObject = fieldsObject.getAsJsonObject("assignee");
                String displayName = assigneeObject.get("displayName").getAsString();
                issue.setAssignee(displayName);
            } else {
                issue.setAssignee("Unassigned");
            }
            

            issue.setKey(key);
            issue.setSummary(summary);
            issues.add(issue);
        }
        
        return issues;
    }


    public List<TeamProgress> getTeamsProgress(String version) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder() 
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=type=" + version))  //aqui se le especifica cuanta info de dicho proyecto queremos traer
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
        .GET()
        .build();

        HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject issueJson = JsonParser.parseString(response.body()).getAsJsonObject();

        JsonArray allProgress = issueJson.getAsJsonArray("progress");
        
        List<TeamProgress> teamProgresses = new ArrayList<>();

        for (JsonElement issueElement : allProgress) {
            TeamProgress teamProgress = new TeamProgress();
            JsonObject issueObject = issueElement.getAsJsonObject();
            String progress = issueObject.get("issueType").getAsString();
            //JsonObject fieldsObject = issueElement.getAsJsonObject().getAsJsonObject("fields");
            teamProgress.setProgress(progress);
        }
        
        return teamProgresses;
}}