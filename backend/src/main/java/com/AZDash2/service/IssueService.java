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

    /*
     * Gets all ISSUES' specified information.
     */
    public List<Issue> getIssues(String projectIdOrKey, String versionGiven) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issueType%20in%20(story%2C%20task)%20AND%20cf[10051]~" + versionGiven + "%20AND%20project=" + projectIdOrKey + "&maxResults=100&fields=id,summary,assignee,creator,created,resolutiondate,customfield_10051,comment"))
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
            JsonObject creatorObject = fieldsObject.get("creator").getAsJsonObject();
            String creator = creatorObject.get("displayName").getAsString();
            String created = fieldsObject.get("created").getAsString();
            String version = fieldsObject.get("customfield_10051").getAsString();

            JsonObject commentsObject = fieldsObject.get("comment").getAsJsonObject();
            JsonArray allcomments = commentsObject.getAsJsonArray("comments");
        
            String lastComment = ""; // Initialize an empty string to hold the last comment
            
            for (JsonElement commentElement : allcomments) {
                JsonObject commentObject = commentElement.getAsJsonObject();
                
                if (!commentObject.get("body").getAsString().toString().equals("null")) {
                    lastComment = commentObject.get("body").getAsString();
                    issue.setComment(lastComment);
                    
                } else {
                    issue.setComment("No comment yet");
                }
            }
    
            if (!fieldsObject.get("assignee").toString().equals("null")) {
                JsonObject assigneeObject = fieldsObject.getAsJsonObject("assignee");
                String displayName = assigneeObject.get("displayName").getAsString();
                issue.setAssignee(displayName);
            } else {
                issue.setAssignee("Unassigned");
            }

            if (!fieldsObject.get("resolutiondate").toString().equals("null")) {
                String resolved = fieldsObject.get("resolutiondate").getAsString();
                issue.setResolved(resolved);
            } else {
                issue.setResolved("Unfinished");
            }
            
            issue.setKey(key);
            issue.setSummary(summary);
            issue.setCreator(creator);
            issue.setCreated(created);
            issue.setVersion(version);
            issues.add(issue);

        }
        
        return issues;
    }

    /*
     * Gets all BUGS' specified information
     */
    public List<Issue> getBugs(String projectIdOrKey, String versionGiven) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issueType%3Dbug%20AND%20cf[10053]~" + versionGiven + "%20AND%20project=" + projectIdOrKey + "&maxResults=100&fields=id,summary,assignee,creator,created,resolutiondate,customfield_10053, comment"))
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
            JsonObject creatorObject = fieldsObject.get("creator").getAsJsonObject();
            String creator = creatorObject.get("displayName").getAsString();
            String created = fieldsObject.get("created").getAsString();
            String version = fieldsObject.get("customfield_10053").getAsString();
            
            JsonObject commentsObject = fieldsObject.get("comment").getAsJsonObject();
            JsonArray allcomments = commentsObject.getAsJsonArray("comments");
        
            String lastComment = ""; // Initialize an empty string to hold the last comment
            
            for (JsonElement commentElement : allcomments) {
                JsonObject commentObject = commentElement.getAsJsonObject();
                
                if (!commentObject.get("body").getAsString().toString().equals("null")) {
                    lastComment = commentObject.get("body").getAsString();
                    issue.setComment(lastComment);
                    
                } else {
                    issue.setComment("No comment yet");
                }
            }

            if (!fieldsObject.get("assignee").toString().equals("null")) {
                JsonObject assigneeObject = fieldsObject.getAsJsonObject("assignee");
                String displayName = assigneeObject.get("displayName").getAsString();
                issue.setAssignee(displayName);
            } else {
                issue.setAssignee("Unassigned");
            }

            if (!fieldsObject.get("resolutiondate").toString().equals("null")) {
                String resolved = fieldsObject.get("resolutiondate").getAsString();
                issue.setResolved(resolved);
            } else {
                issue.setResolved("Unfinished");
            }
            
            issue.setKey(key);
            issue.setSummary(summary);
            issue.setCreator(creator);
            issue.setCreated(created);
            issue.setVersion(version);
            issues.add(issue);

        }
        
        return issues;
    }
    /*
     * Gets the  percent amount stated on Jira´s custom field "Progress" of all tickets of type "TeamProgress"
     */
    public List<TeamProgress> getTeamsProgress() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder() 
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issuetype=teamprogress"))
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
        .GET()
        .build();

        HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject issueJson = JsonParser.parseString(response.body()).getAsJsonObject();

        JsonArray issues = issueJson.getAsJsonArray("issues");
        List<TeamProgress> teamProgresses = new ArrayList<>();

        for (JsonElement issueElement : issues) {
            TeamProgress teamProgress = new TeamProgress();

            JsonObject issueObject = issueElement.getAsJsonObject();
            JsonObject fieldsObject = issueObject.getAsJsonObject("fields");
            String progress = fieldsObject.get("customfield_10049").getAsString();
            JsonObject teamObject = fieldsObject.getAsJsonObject("customfield_10048");
            String team = teamObject.get("value").getAsString();
            String version = fieldsObject.get("customfield_10046").getAsString();

            teamProgress.setProgress(progress);
            teamProgress.setTeam(team);
            teamProgress.setVersion(version);
            teamProgresses.add(teamProgress);
        }
        
        return teamProgresses;
    }
    /*
     * Gets the  percent amount stated on Jira´s custom field "Progress" for all tickets of type "TeamProgress" and specified version
     */
    public List<TeamProgress> getProgressByVersion(String versionGiven) 
    throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issuetype=teamprogress%20AND%20cf[10046]~" + versionGiven + "&fields=customfield_10049,customfield_10048,value,customfield_10046"))
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
        .GET()
        .build();

        HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject issueJson = JsonParser.parseString(response.body()).getAsJsonObject();

        JsonArray issues = issueJson.getAsJsonArray("issues");
        List<TeamProgress> teamProgresses = new ArrayList<>();

        for (JsonElement issueElement : issues) {
            TeamProgress teamProgress = new TeamProgress();

            JsonObject issueObject = issueElement.getAsJsonObject();
            JsonObject fieldsObject = issueObject.getAsJsonObject("fields");
            String progress = fieldsObject.get("customfield_10049").getAsString();
            JsonObject teamObject = fieldsObject.getAsJsonObject("customfield_10048");
            String team = teamObject.get("value").getAsString();
            String version = fieldsObject.get("customfield_10046").getAsString();

            teamProgress.setProgress(progress);
            teamProgress.setTeam(team);
            teamProgress.setVersion(version);
            teamProgresses.add(teamProgress);
        }
        
        return teamProgresses;
    }
}

