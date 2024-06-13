package com.AZDash2.service;

import com.AZDash2.entity.Release;
import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.ReleaseHistoricalRepository;
import com.AZDash2.repository.ReleaseRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.math.BigDecimal;
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
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReleaseHistoricalService {
  private ReleaseRepository releaseRepository;
  @Autowired private ReleaseHistoricalRepository releaseHistoricalRepository;

  @Value("${jira.progress.project}")
  private String jiraProgressProject;

  public Optional<ReleaseHistorical> getIssuesByDateAndRelease(Date date, Long idRelease) {
    return releaseHistoricalRepository.findByDateAndIdRelease(date, idRelease).stream().findFirst();
  }

  public Optional<ReleaseHistorical> getProgressByDateAndRelease(Date date, Long idRelease) {
    return releaseHistoricalRepository
        .findByDateBeforeAndReleaseIdOrderByRecordDateDescRecordTimeDesc(date, idRelease)
        .stream()
        .findFirst();
  }

  public List<Optional<ReleaseHistorical>> getByDate(Date date) {
    List<Optional<ReleaseHistorical>> releaseHistoricals = new ArrayList<>();
    List<Release> releases = releaseHistoricalRepository.findDistinctReleaseIds();
    for (int release = 0; release < releases.size(); release++) {
      if (releases.get(release).getCreation_date().compareTo(date) <= 0
          && releases.get(release).getCurr_release_date().compareTo(date) >= 0) {
        Optional<ReleaseHistorical> releaseHistorical =
            getProgressByDateAndRelease(date, releases.get(release).getId_release());

        if (releaseHistorical.isPresent()) {
          releaseHistoricals.add(releaseHistorical);
        }
      }
    }

    return releaseHistoricals;
  }

  ////
  Logger logger = LoggerFactory.getLogger(IssueService.class);

  @Value("${jira.api.url}")
  private String jiraApiUrl;

  @Value("${jira.api.token}")
  private String jiraApiToken;

  /*
   * Gets the percent amount stated on Jira´s custom field "Progress" for all
   * tickets of type "TeamProgress" and specified version
   */
    public ReleaseHistorical getProgressByVersion(String versionGiven)
    throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =
        HttpRequest.newBuilder()
        .uri(
                new URI(
                    jiraApiUrl
                        + "/rest/api/2/search?jql=issuetype=teamprogress%20AND%20cf[10046]~"
                        + versionGiven
                        + "%20AND%20project="
                        + jiraProgressProject
                        + "&fields=customfield_10049,customfield_10048,value,customfield_10046"))
            .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
            .GET()
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
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
        teamProgress.setPercent_third_party(progress);
        ;
    } else if (team.equals("UAT")) {
        teamProgress.setPercent_uat(progress);
    }
    }

    return teamProgress;
    }


  public ReleaseHistorical getProgressByVersionAZ(String versionGiven)
      throws URISyntaxException, IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(
                new URI(
                    jiraApiUrl
                    + "/rest/api/2/search?jql=project="+ jiraProgressProject +"%20AND%20fixVersion="
                    + versionGiven
                    + "&fields=summary,customfield_10803"))
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + jiraApiToken)
            .GET()
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    logger.debug("Response Http Status {}", response.statusCode());
    logger.debug("Response Body {}", response.body());

    JsonObject issueJson = JsonParser.parseString(response.body()).getAsJsonObject();
    JsonArray issues = issueJson.getAsJsonArray("issues");

    ReleaseHistorical teamProgress = new ReleaseHistorical();
    for (JsonElement issueElement : issues) {
      JsonObject issueObject = issueElement.getAsJsonObject();
      JsonObject fieldsObject = issueObject.getAsJsonObject("fields");
      JsonObject progressObject = fieldsObject.getAsJsonObject("customfield_10803");
      String progressString = progressObject.get("value").getAsString();
      String progressWithoutPercent = progressString.replace("%", "");
      BigDecimal progress = new BigDecimal(progressWithoutPercent);
      String team = fieldsObject.get("summary").getAsString();

      if (team.equals("QA")) {
        teamProgress.setPercent_qa(progress);
      } else if (team.equals("PT")) {
        teamProgress.setPercent_pt(progress);
      } else if (team.equals("3RDP")) {
        teamProgress.setPercent_third_party(progress);
        ;
      } else if (team.equals("UAT")) {
        teamProgress.setPercent_uat(progress);
      }
    }

    return teamProgress;
  }

  @Autowired
  public void SaveTeamProgressService(
      ReleaseRepository releaseRepository,
      ReleaseHistoricalRepository releaseHistoricalRepository) {
    this.releaseRepository = releaseRepository;
    this.releaseHistoricalRepository = releaseHistoricalRepository;
  }

  public List<ReleaseHistorical> getAndSaveProgressReleases(String projectIdOrKey)
      throws URISyntaxException, IOException, InterruptedException {
    List<Release> releases = releaseRepository.findByStatus("On Time");
    List<ReleaseHistorical> progressReleases = new ArrayList<>();
    LocalDate currentDate = LocalDate.now(ZoneId.of("America/Chihuahua"));
    LocalTime currentTime = LocalTime.now(ZoneId.of("America/Chihuahua"));
    for (Release release : releases) {

      try {
        ReleaseHistorical teamProgress =
            getProgressByVersion(release.getName() + "-release-" + release.getVersion());
        if (teamProgress != null) {
          teamProgress.setRecordDate(Date.valueOf(currentDate));
          teamProgress.setRelease(release);
          teamProgress.setRecordTime(Time.valueOf(currentTime));

          if (currentTime.getHour() == 0 && currentTime.getMinute() == 0) {
            logger.info("Saving team progress at midnight for release: {}", release.getName());
            releaseHistoricalRepository.save(teamProgress);
            progressReleases.add(teamProgress);
          } else {
            logger.info("Updating team progress for release: {}", release.getName());
            List<ReleaseHistorical> lastRecords =
                releaseHistoricalRepository.findTopByReleaseOrderByRecordDateDescRecordTimeDesc(
                    release.getId_release());
            if (!lastRecords.isEmpty()) {
              ReleaseHistorical updateRecord =
                  lastRecords.get(0); // Tomar el primer registro como el más reciente
              updateRecord.setPercent_qa(teamProgress.getPercent_qa());
              updateRecord.setPercent_uat(teamProgress.getPercent_uat());
              updateRecord.setPercent_third_party(teamProgress.getPercent_third_party());
              updateRecord.setPercent_pt(teamProgress.getPercent_pt());
              updateRecord.setRecordDate(Date.valueOf(currentDate));
              updateRecord.setRecordTime(Time.valueOf(currentTime));
              releaseHistoricalRepository.save(updateRecord);
              progressReleases.add(updateRecord);
              logger.info("Updated team progress for release: {}", release.getName());
            } else {
              logger.info(
                  "No previous record found, saving new team progress for release: {}",
                  release.getName());
              releaseHistoricalRepository.save(teamProgress);
              progressReleases.add(teamProgress);
            }
          }
        } else {
          logger.error(
              "No progress data found for version: {} and name: {}",
              release.getVersion(),
              release.getName());
        }
      } catch (Exception e) {
        logger.error(
            "Error saving progress for version: {} and name: {}",
            release.getVersion(),
            release.getName(),
            e);
      }
    }
    return progressReleases;
  }

  @Scheduled(cron = "0 0 * * * *", zone = "America/Chihuahua")
  public void scheduledTask() {
    try {
      getAndSaveProgressReleases(jiraApiToken);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
