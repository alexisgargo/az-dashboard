package com.AZDash2.controller;

import com.AZDash2.entity.Issue;
import com.AZDash2.service.DBIssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("az_dashboard")
public class DBIssueController {

  @Autowired DBIssueService issueService;

  @Operation(summary = "Get all the Issues")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "All Issues found",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = Issue.class)))
            }),
        @ApiResponse(
            responseCode = "500",
            description = "An error occurred while fetching the Issues",
            content = @Content)
      })
  @GetMapping("/issues")
  public ResponseEntity<List<Issue>> getIssues() {
    List<Issue> issues = issueService.getIssues();
    return new ResponseEntity<>(issues, HttpStatus.OK);
  }

  @Operation(summary = "Create a new Issue")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Issue created successfully",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Issue.class))
            }),
        @ApiResponse(
            responseCode = "500",
            description = "An error occurred while creating the Issue",
            content = @Content)
      })
  @PostMapping("/issue")
  public ResponseEntity<Issue> saveIssues(@RequestBody @Valid Issue issues) {
    try {
      issueService.saveIssues(issues);
      return new ResponseEntity<>(issues, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Get the latest Issues by date and Release")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Latest Issues by date and Release found",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = Issue.class)))
            }),
        @ApiResponse(
            responseCode = "500",
            description = "An error occurred while fetching the Issues",
            content = @Content)
      })
  @GetMapping("/issues/{date}/{idRelease}")
  public ResponseEntity<List<Issue>> getLatestIssuesByDateAndRelease(
      @PathVariable("date") Date date, @PathVariable("idRelease") Long idRelease) {
    List<Issue> issues;
    try {
      issues = issueService.getLatestIssuesByDateAndRelease(date, idRelease);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(issues, HttpStatus.OK);
  }

  @Operation(summary = "Count the latest Issues by date and Release")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Count of latest Issues by date and Release found",
            content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid date or release ID provided",
            content = @Content),
        @ApiResponse(
            responseCode = "204",
            description = "No issues found for the provided date and release ID",
            content = @Content)
      })
  @GetMapping("/issues/count/{date}/{idRelease}")
  public ResponseEntity<?> countLatestIssuesByDateAndRelease(
      @PathVariable("date") Date date, @PathVariable("idRelease") Long idRelease) {

    if (date == null || idRelease == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Invalid date or release ID provided");
    }

    Map<String, Long> counts = issueService.countLatestIssuesByDateAndRelease(date, idRelease);
    if (counts == null
        || counts.isEmpty()
        || (counts.get("issues") == 0 && counts.get("bugs") == 0)) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok(counts);
  }
}
