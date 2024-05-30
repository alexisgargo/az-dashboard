package com.AZDash2.service;

import com.AZDash2.entity.Issue;
import com.AZDash2.entity.Release;
import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.IssueRepository;
import com.AZDash2.repository.ReleaseHistoricalRepository;
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

        return processHttpResponse(response);
    }

    private List<Issue> processHttpResponse(HttpResponse<String> response) {

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
            issue.setEnvironment("No environemnts on issues");
            issues.add(issue);

        }

        return issues;
    }

    /*
     * Gets all BUGS' specified information
     */
    public List<Issue> getBugs(String projectIdOrKey, String versionGiven)
            throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issueType%3Dbug%20AND%20cf[10053]~" + versionGiven
                        + "%20AND%20project=" + projectIdOrKey
                        + "&maxResults=100&fields=id,summary,assignee,creator,created,resolutiondate,customfield_10053,customfield_10055,comment"))
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

            if (!fieldsObject.get("customfield_10055").isJsonNull()) {
                JsonObject environmentObject = fieldsObject.getAsJsonObject("customfield_10055");
                String environment = environmentObject.get("value").getAsString();
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
                String resolved = fieldsObject.get("resolutiondate").getAsString();
                bug.setIssue_status(resolved);
            } else {
                bug.setIssue_status("Unfinished");
            }

            bug.setIssue_number(issue_number);
            bug.setIssue_summary(issue_summary);
            bug.setCreated_by(created_by);
            bug.setCreation_date(creation_date);
            //bug.setEnvironment(environment);
            bugs.add(bug);

        }

        return bugs;
    }

    public Map<String, Long> getTicketAmount(String projectIdOrKey)
            throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestBugs = HttpRequest.newBuilder()
                .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issuetype%3Dbug%20AND%20project=" + projectIdOrKey
                        + "%20AND%20status%21%3DDone&maxResults=0"))
                .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
                .GET()
                .build();

        HttpResponse<String> response = client.send(requestBugs,
                HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject bugsJson = JsonParser.parseString(response.body()).getAsJsonObject();

        HttpRequest requestIssue = HttpRequest.newBuilder() // issueType%20in%20(story%2C%20task)
                .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issueType%20in%20(story%2C%20task)%20AND%20project="
                        + projectIdOrKey + "&maxResults=0"))
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
        amount.put("bugs", Bugnumber);
        amount.put("issues", issueNumber);
        return amount;

    }

   
    public List<Issue> getAndSaveIssues(String projectIdOrKey)
            throws URISyntaxException, IOException, InterruptedException {
        List<Release> releases = releaseRepository.findByStatus("On Time");
        //List<ReleaseHistorical> progressReleases = new ArrayList<>();
        LocalDate currentDate = LocalDate.now(ZoneId.of("America/Chihuahua"));
        LocalTime currentTime = LocalTime.now(ZoneId.of("America/Chihuahua"));
        List<Issue> savedIssues= new ArrayList<>();
        for (Release release : releases) {
            List<Issue> issueInformation = getIssues(release.getName(), release.getVersion() );
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
                        //me traigo primer issue
                        Issue foundIssue= issuesFound.get(0);
                        //aqui tengo que poner los set de todos los atributos 
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
    //@Scheduled(cron = "0 0 * * * *", zone = "America/Chihuahua")
    @Scheduled(cron = "0 */1 * * * * ", zone = "America/Chihuahua")
    public void scheduledTask() {
        try {
            getAndSaveIssues("DAS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
