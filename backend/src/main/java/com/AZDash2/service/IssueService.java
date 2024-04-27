package com.AZDash2.service;

import com.AZDash2.entity.Issue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Issue> getIssues(String projectIdOrKey, String versionGiven)
            throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issueType%20in%20(story%2C%20task)%20AND%20cf[10051]~"
                        + versionGiven + "%20AND%20project=" + projectIdOrKey
                        + "&maxResults=100&fields=id,summary,assignee,creator,created,resolutiondate,customfield_10051,comment"))
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
            String issue_number = issueObject.get("key").getAsString();
            JsonObject fieldsObject = issueElement.getAsJsonObject().getAsJsonObject("fields");
            String issue_summary = fieldsObject.get("summary").getAsString();
            JsonObject creatorObject = fieldsObject.get("creator").getAsJsonObject();
            String created_by = creatorObject.get("displayName").getAsString();
            String creation_date = fieldsObject.get("created").getAsString();
            String description = fieldsObject.has("description") ? fieldsObject.get("description").getAsString() : "No description available";
            
            

            JsonObject commentsObject = fieldsObject.get("comment").getAsJsonObject();
            JsonArray allcomments = commentsObject.getAsJsonArray("comments");

            String lastComment = "";

            for (JsonElement commentElement : allcomments) {
                JsonObject commentObject = commentElement.getAsJsonObject();

                if (!commentObject.get("body").getAsString().toString().equals("null")) {
                    lastComment = commentObject.get("body").getAsString();
                    issue.setUpdates(lastComment);

                } else {
                    issue.setUpdates("No comment yet");
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
                String issue_status = fieldsObject.get("resolutiondate").getAsString();
                issue.setIssue_status(issue_status);
            } else {
                issue.setIssue_status("Unfinished");
            }

            issue.setIssue_number(issue_number);
            issue.setIssue_summary(issue_summary);
            issue.setCreated_by(created_by);
            issue.setCreation_date(creation_date);
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
            .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issueType%3Dbug%20AND%20cf[10053]~" + versionGiven + "%20AND%20project=" + projectIdOrKey + "&maxResults=100&fields=id,summary,assignee,creator,created,resolutiondate,customfield_10053,customfield_10055,comment"))
            .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
            .GET()
            .build();

            HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
            logger.debug("Response Http Status {}", response.statusCode());
            logger.debug("Response Body {}", response.body());

            JsonObject issueJson = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray allBugs = issueJson.getAsJsonArray("issues");
            List<Issue> bugs = new ArrayList<>();

            for (JsonElement issueElement : allBugs) {
                Issue bug = new Issue();
            JsonObject issueObject = issueElement.getAsJsonObject();
            String issue_number = issueObject.get("key").getAsString();
            JsonObject fieldsObject = issueElement.getAsJsonObject().getAsJsonObject("fields");
            String issue_summary = fieldsObject.get("summary").getAsString();
            JsonObject creatorObject = fieldsObject.get("creator").getAsJsonObject();
            String created_by = creatorObject.get("displayName").getAsString();
            String creation_date = fieldsObject.get("created").getAsString();

            JsonObject environmentObject = fieldsObject.getAsJsonObject("customfield_10055");
            String environment = environmentObject.get("value").getAsString();

            JsonObject commentsObject = fieldsObject.get("comment").getAsJsonObject();
            JsonArray allcomments = commentsObject.getAsJsonArray("comments");

            String lastComment = "";

            for (JsonElement commentElement : allcomments) {
                JsonObject commentObject = commentElement.getAsJsonObject();

                if (!commentObject.get("body").getAsString().toString().equals("null")) {
                    lastComment = commentObject.get("body").getAsString();
                    bug.setUpdates(lastComment);
                } else {
                    bug.setUpdates("No comment yet");
                }
            }

            if (!fieldsObject.get("assignee").toString().equals("null")) {
                JsonObject assigneeObject = fieldsObject.getAsJsonObject("assignee");
                String displayName = assigneeObject.get("displayName").getAsString();
                bug.setAssignee(displayName);
            } else {
                bug.setAssignee("Unassigned");
            }

            if (!fieldsObject.get("resolutiondate").toString().equals("null")) {
                String resolved = fieldsObject.get("resolutiondate").getAsString();
                bug.setIssue_status(resolved);
            } else {
                bug.setIssue_status("Unfinished");
            }

            bug.setIssue_number(issue_number);
            bug.setIssue_summary(issue_summary);
            bug.setCreated_by(created_by);
            bug.setCreation_date(creation_date);
            bug.setEnvironment(environment);
            bugs.add(bug);

        }

        return bugs;
    }

    public Map<String, Long> getTicketAmount(String projectIdOrKey) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestBugs = HttpRequest.newBuilder()
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issuetype%3Dbug%20AND%20project=" + projectIdOrKey + "%20AND%20status%21%3DDone&maxResults=0"))
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken) 
        .GET()
        .build();

        HttpResponse<String> response = client.send(requestBugs,
        HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject bugsJson = JsonParser.parseString(response.body()).getAsJsonObject();

        HttpRequest requestIssue = HttpRequest.newBuilder() //issueType%20in%20(story%2C%20task)
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issueType%20in%20(story%2C%20task)%20AND%20project=" + projectIdOrKey + "&maxResults=0"))
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken) 
        .GET()
        .build();
        HttpResponse<String> responseIssue = client.send(requestIssue,
        HttpResponse.BodyHandlers.ofString());
        JsonObject issueJson = JsonParser.parseString(responseIssue.body()).getAsJsonObject();

        Map<String, Long> amount = new HashMap<>();
        String issueAmountStr = issueJson.get("total").getAsString();
        String bugAmountStr = bugsJson.get("total").getAsString();
        Long issueNumber = Long.parseLong(issueAmountStr);
        Long Bugnumber = Long.parseLong(bugAmountStr);
        amount.put("Bugs:",Bugnumber);
        amount.put("Issues:",issueNumber);
        return amount;

    }

}
