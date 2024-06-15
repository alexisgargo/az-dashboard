package com.AZDash2.service;

import com.AZDash2.entity.Issue;
import com.AZDash2.valueobject.Changelog;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.IssueRepository;
import com.AZDash2.repository.ReleaseRepository;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class IssueService {
    Logger logger = LoggerFactory.getLogger(IssueService.class);
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ReleaseRepository releaseRepository;
     
    @Value("${jira.api.url}")
    private String jiraApiUrl;

    @Value("${jira.api.token}")
    private String jiraApiToken;

    @Autowired
    HttpClient client;

    @Value("#{'${jira.project.list}'.split(',')}")
    private List<String> projectList; 

    @Value("${jira.bugs.project}")
    private String jiraBugsProject;

    
    public List<Issue> getIssuesFromProjectList(String projectIdOrKey, String versionGiven)
    throws URISyntaxException, IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=project=" + projectIdOrKey + "%20AND%20fixVersion=" + versionGiven
            + "&maxResults=1000&fields=fixVersions,id,summary,assignee,creator,created,resolutiondate,comment,status"))
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + jiraApiToken)
            .GET()
            .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        return processHttpResponse(response.body());
        
    }

    public List<Issue> getIssuesOfGivenVersionFromAllProjects(String versionGiven) throws URISyntaxException, IOException, InterruptedException {
        List<Issue> issues = new ArrayList<>();
        for (int i = 0; i < projectList.size(); ++i){
            List<Issue> issuesFromProject = getIssuesFromProjectList(projectList.get(i), versionGiven);
            issues.addAll(issuesFromProject);

        }
        return issues;
    }

    private List<Issue> processHttpResponse(String jsonString) {
        
        JsonObject issueJson = JsonParser.parseString(jsonString).getAsJsonObject();
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

            JsonObject commentsObject = fieldsObject.get("comment").getAsJsonObject();
            JsonArray allcomments = commentsObject.getAsJsonArray("comments");

            JsonObject statusObject = fieldsObject.get("status").getAsJsonObject();
            String status = statusObject.get("name").getAsString();

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
            issue.setEnvironment("No environemnts on issues");
            issue.setIssue_status(status);
            issues.add(issue);

        }

        return issues;
    }

    public List<Issue> getBugsFromGivenRelease(String versionGiven)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=project=" + jiraBugsProject + "%20AND%20fixVersion=" + versionGiven
                + "&maxResults=100&fields=fixVersions,id,summary,assignee,creator,created,resolutiondate,comment,status,environment"))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jiraApiToken)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        return processHttpResponseBugsAZ(response.body());
    }

    private List<Issue> processHttpResponseBugsAZ(String jsonString) {
        JsonObject issueJson = JsonParser.parseString(jsonString).getAsJsonObject();
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
            JsonObject statusObject = fieldsObject.get("status").getAsJsonObject();

            if (!fieldsObject.get("environment").isJsonNull()) {  
                String environment = fieldsObject.get("environment").getAsString();
                bug.setEnvironment(environment);
            } else {
                 bug.setEnvironment("No environment set");
            }

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
                String status = statusObject.get("name").getAsString();
                bug.setIssue_status(status);
            } else {
                bug.setIssue_status("no Status on this bug");
            }

            bug.setIssue_number(issue_number);
            bug.setIssue_summary(issue_summary);
            bug.setCreated_by(created_by);
            bug.setCreation_date(creation_date);
            bugs.add(bug);

        }

        return bugs;
    }


    public Map<String, Long> getTicketAmount(String projectIdOrKey) throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> response = getResponseBugs(projectIdOrKey);
        JsonObject bugsJson = JsonParser.parseString(response.body()).getAsJsonObject();
        System.out.println(bugsJson);
        logger.debug("Bugsjson", bugsJson);

        HttpResponse<String> responseIssue = getResponseIssue(projectIdOrKey);
        JsonObject issueJson = JsonParser.parseString(responseIssue.body()).getAsJsonObject();
        logger.debug("Issuejson", issueJson);
        System.out.println(issueJson);

        Map<String, Long> amount = new HashMap<>();
        String issueAmountStr = issueJson.get("total").getAsString();
        String bugAmountStr = bugsJson.get("total").getAsString();
        Long issueNumber = Long.parseLong(issueAmountStr);
        Long Bugnumber = Long.parseLong(bugAmountStr);
        amount.put("bugs",Bugnumber);
        amount.put("issues",issueNumber);
        return amount;

    }

    private HttpResponse<String> getResponseIssue(String projectIdOrKey)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest requestIssue = HttpRequest.newBuilder()
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issueType%20in%20(story%2C%20task)%20AND%20project=" + projectIdOrKey + "&maxResults=0"))
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken) 
        .GET()
        .build();
        HttpResponse<String> responseIssue = client.send(requestIssue,
        HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", responseIssue.statusCode());
        logger.debug("Response Body {}", responseIssue.body());
        return responseIssue;
    }
    
    private HttpResponse<String> getResponseBugs(String projectIdOrKey)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest requestBugs = HttpRequest.newBuilder()
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issuetype%3Dbug%20AND%20project=" + projectIdOrKey + "%20AND%20status%21%3DDone&maxResults=0"))
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken) 
        .GET()
        .build();
        HttpResponse<String> response = client.send(requestBugs,
        HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());
        return response;
    }

   
    public List<Issue> getAndSaveIssues(String projectIdOrKey)
            throws URISyntaxException, IOException, InterruptedException {
        List<Release> releases = releaseRepository.findByStatus("In progress");
        LocalDate currentDate = LocalDate.now(ZoneId.of("America/Chihuahua"));
        LocalTime currentTime = LocalTime.now(ZoneId.of("America/Chihuahua"));
        List<Issue> savedIssues= new ArrayList<>();
        for (Release release : releases) {
            List<Issue> issueInformation = getIssuesOfGivenVersionFromAllProjects(release.getVersion());
            try {
                for (Issue issue : issueInformation) {

                    issue.setRecord_date(Date.valueOf(currentDate));
                    issue.setRelease(release);
                    issue.setRecord_time(Time.valueOf(currentTime));
                    List<Issue> issuesFound= issueRepository.findByIssueNumber(issue.getIssue_number());
                    if (issuesFound.isEmpty()) {
                        
                        Issue savedIssue=issueRepository.save(issue);
                        savedIssues.add(savedIssue);
                    }
                    else{
                        Issue foundIssue= issuesFound.get(0);
                        foundIssue.setAssignee(issue.getAssignee());
                        foundIssue.setCreated_by(issue.getCreated_by());
                        foundIssue.setCreation_date(issue.getCreation_date());
                        foundIssue.setEnvironment(issue.getEnvironment());
                        foundIssue.setIs_feature(issue.isIs_feature());
                        foundIssue.setIssue_number(issue.getIssue_number());
                        foundIssue.setIssue_status(issue.getIssue_status());
                        foundIssue.setIssue_summary(issue.getIssue_summary());
                        foundIssue.setUpdates(issue.getUpdates());
                        Issue updatedIssue =issueRepository.save(foundIssue);
                        savedIssues.add(updatedIssue);
                    }
                }
            } catch (Exception e) {
                logger.error("Error saving issues for version: {} and name: {}", release.getVersion(),
                        release.getName(), e);
            }
        }
        return savedIssues;
    }
    
    @Scheduled(cron = "0 1 * * * *", zone = "America/Chihuahua")
    public void scheduledgetAndSaveIssues() {
        try {
            getAndSaveIssues(jiraApiToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Changelog> getChangelogs(String issueIdOrKey) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestChangelog = HttpRequest.newBuilder()
        .uri(new URI(jiraApiUrl + "/rest/api/3/issue/" + issueIdOrKey + "/changelog"))
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken) 
        .GET()
        .build();

        HttpResponse<String> response = client.send(requestChangelog,
        HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject ChangelogJson = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray allChanges = ChangelogJson.getAsJsonArray("values");
        List<Changelog> values = new ArrayList<>();

        
        for (JsonElement valuesElement : allChanges) {
            
            Changelog value = new Changelog();
            JsonObject authorObject = valuesElement.getAsJsonObject().getAsJsonObject("author");
            String value_author = authorObject.get("displayName").getAsString();
            JsonObject valuesObject = valuesElement.getAsJsonObject();            
            String value_date = valuesObject.get("created").getAsString();

            JsonArray itemsArray = valuesElement.getAsJsonObject().getAsJsonArray("items");
            JsonObject itemObject = itemsArray.get(0).getAsJsonObject();
            String field = itemObject.get("field").getAsString();
            if ("Progress".equals(field)) {
                String value_from = itemObject.get("fromString").getAsString();
                String value_to = itemObject.get("toString").getAsString();
                value.setFrom(value_from);
                value.setTo(value_to);
                value.setWho(value_author);
                value.setWhen(value_date);
                values.add(value);
            }
        }

        return values;

    }



}
