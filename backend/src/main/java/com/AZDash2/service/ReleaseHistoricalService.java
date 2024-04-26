package com.AZDash2.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import org.springframework.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.ReleaseHistoricalRepository;

@Service
public class ReleaseHistoricalService {

    @Autowired
    private ReleaseHistoricalRepository releaseHistoricalRepository;

    public Optional<ReleaseHistorical> getIssuesByDateAndRelease(Date date, Long idRelease) {
        return releaseHistoricalRepository.findByDateBeforeAndIdRelease(date, idRelease).stream().findFirst();
    }

    ////
    Logger logger = LoggerFactory.getLogger(IssueService.class);
    @Value("${jira.api.url}")
    private String jiraApiUrl;

    @Value("${jira.api.token}")
    private String jiraApiToken;

    /*
     * Gets the  percent amount stated on Jira´s custom field "Progress" for all tickets of type "TeamProgress" and specified version
     */
    public ReleaseHistorical getProgressByVersion(String versionGiven, String projectIdOrKey) 
    throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder() 
        .uri(new URI(jiraApiUrl + "/rest/api/2/search?jql=issuetype=teamprogress%20AND%20cf[10046]~" + versionGiven + "%20AND%20project=" + projectIdOrKey + "&fields=customfield_10049,customfield_10048,value,customfield_10046"))
        .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
        .GET()
        .build();

        HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject issueJson = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray issues = issueJson.getAsJsonArray("issues");

        ReleaseHistorical teamProgress = new ReleaseHistorical();
        for (JsonElement issueElement : issues) {

            JsonObject issueObject = issueElement.getAsJsonObject();
            JsonObject fieldsObject = issueObject.getAsJsonObject("fields");
            BigDecimal progress = fieldsObject.get("customfield_10049").getAsBigDecimal();
            JsonObject teamObject = fieldsObject.getAsJsonObject("customfield_10048");
            String team = teamObject.get("value").getAsString();

            if (team.equals("QA")) {
                teamProgress.setPercent_qa(progress);
            } else if (team.equals("PT")) {
                teamProgress.setPercent_pt(progress);
            } else if (team.equals("3rd-Party")) {
                teamProgress.setPercent_third_party(progress);;
            } else if (team.equals("UAT")) {
                teamProgress.setPercent_uat(progress);
            }

        }
        
        return teamProgress;
    }

}
