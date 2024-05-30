package com.AZDash2.controller;

import com.AZDash2.entity.Release;
import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.service.ReleaseHistoricalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("az_dashboard")
public class ReleaseHistoricalController {
  Logger logger = LoggerFactory.getLogger(IssueController.class);

  @Autowired
  ReleaseHistoricalService releaseHistoricalService;

  @Operation(summary = "Get Release Teams' Progress by date and Release ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all team's historical progress", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ReleaseHistorical.class))
      }),
      @ApiResponse(responseCode = "400", description = "Bad Request: Invalid date or idRelease", content = @Content),
      @ApiResponse(responseCode = "404", description = "Release Historical not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping("/historical/{date}/{idRelease}")
  public ResponseEntity<ReleaseHistorical> getIssuesByDateAndRelease(
      @PathVariable("date") Date date, @PathVariable("idRelease") Long idRelease) {
    Optional<ReleaseHistorical> record;

    if (date == null || idRelease == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    record = releaseHistoricalService.getProgressByDateAndRelease(date, idRelease);

    if (!record.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(record.get(), HttpStatus.OK);
  }

  @Operation(summary = "Get a list of releases with their progress and information for a given date", description = "Get a list of releases with their progress and information for a given date")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Releases were found", content = {
          @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Release.class))) }),
      @ApiResponse(responseCode = "404", description = "Releases were not found", content = @Content)
  })
  @GetMapping("/releases-historicals/{date}")
  public ResponseEntity<List<Optional<ReleaseHistorical>>> getReleasesByDate(@PathVariable Date date) {
    List<Optional<ReleaseHistorical>> releaseHistoricals = new ArrayList<>();

    releaseHistoricals = releaseHistoricalService.getByDate(date);

    if (releaseHistoricals == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT); // NO USAR NOT_FOUND, USAR NO_CONTENT
    }

    return new ResponseEntity<>(releaseHistoricals, HttpStatus.OK);
  }

  @Operation(summary = "Gets all dev team's current percent status")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all team's status", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReleaseHistorical.class), examples = {
          @ExampleObject(name = "Teams progress", value = "{\"percent_qa\": 10, \"percent_uat\": 25, \"percent_third_party\""
              + " :50, \"percent_pt\" : 75 }", description = "The API pulled the percent amount manually set in Jira's Custom"
                  + " Issue Type: Team Progress")
      })),
      @ApiResponse(responseCode = "400", description = "Invalid project name or version", content = @Content),
      @ApiResponse(responseCode = "404", description = "Project not found", content = @Content)
  })
  @GetMapping("/progress/{projectIdOrKey}/{version}")
  public ResponseEntity<ReleaseHistorical> pullProgressByVersion(
      @PathVariable String version, @PathVariable String projectIdOrKey) {
    ReleaseHistorical teamProgress;
    try {
      teamProgress = releaseHistoricalService.getProgressByVersion(version, projectIdOrKey);

    } catch (URISyntaxException | IOException | InterruptedException e) {
      logger.error("Progress JIRA API failed", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(teamProgress, HttpStatus.OK);
  }

  @Operation(summary = "Get progress of all Releases")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of Release Historical found", content = {
          @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReleaseHistorical.class)))
      }),
      @ApiResponse(responseCode = "204", description = "No content", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @GetMapping("/team-progress")
  public ResponseEntity<List<ReleaseHistorical>> getProgressReleases() {
    try {
      List<ReleaseHistorical> progressReleases = releaseHistoricalService.getAndSaveProgressReleases(null);
      if (progressReleases.isEmpty()) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(progressReleases);
    } catch (Exception e) {
      // Manejo de la excepción
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }
}
