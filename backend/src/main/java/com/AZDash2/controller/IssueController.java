package com.AZDash2.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.AZDash2.entity.Issue;
import com.AZDash2.service.IssueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;




@RestController
public class IssueController {
    
    Logger logger = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    IssueService issueService;

    @Operation(summary = "Get all ISSUES from Jira - given its project name and version")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Issues from given project and version found",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Issue.class))
            )
        ),
        @ApiResponse(responseCode = "400", description = "Invalid project name or version", 
        content = @Content),
        @ApiResponse(responseCode = "404", description = "Project not found", 
        content = @Content)
    })
    @GetMapping("/issue/{projectIdOrKey}/{versionGiven}")
    public ResponseEntity<List<Issue>> pullIssues(@PathVariable String projectIdOrKey, @PathVariable String versionGiven) {
    List<Issue> issues;
        try {
            issues = issueService.getIssues(projectIdOrKey, versionGiven);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("JIRA API failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
        }

        return new ResponseEntity<>(issues, HttpStatus.OK);
    }


    @Operation(summary = "Pull all BUGS from Jira of given project name and version")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bugs from given project and version found",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Issue.class))
            )
        ),
        @ApiResponse(responseCode = "400", description = "Invalid project name or version", 
        content = @Content),
        @ApiResponse(responseCode = "404", description = "Project not found", 
        content = @Content)
    })
    @GetMapping("/bugs/{projectIdOrKey}/{versionGiven}")
    public ResponseEntity<List<Issue>> pullBugs(@PathVariable String projectIdOrKey, @PathVariable String versionGiven) {
    List<Issue> bugs;
        try {
            bugs = issueService.getBugs(projectIdOrKey, versionGiven);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("JIRA API failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
        }

        return new ResponseEntity<>(bugs, HttpStatus.OK);
    }
    

@Operation(summary = "Returns total amount of bugs (that remain open) and issues")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the total counts of open bugs and issues, categorized separately.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(
                implementation = Map.class, 
                type = "object", 
                description = "A map of categories with their respective counts"
            ),
            examples = {
                @ExampleObject(
                    name = "Example response",
                    value = "{\"bugs\": 2, \"issues\": 25}",
                    description = "Returns amount of issues and bugs with an 'open' status"
                )
            }
        )
    ),
    @ApiResponse(responseCode = "400", description = "Invalid request parameters", 
        content = @Content),
    @ApiResponse(responseCode = "404", description = "Data not found", 
        content = @Content)
})
    @GetMapping("/count/{projectIdOrKey}")
    public ResponseEntity<Map<String, Long>> pullTicketAmount(@PathVariable String projectIdOrKey) {
        Map<String, Long> amount;
            try {
                amount = issueService.getTicketAmount(projectIdOrKey);
            } catch (URISyntaxException | IOException | InterruptedException e) {
                logger.error("JIRA API failed", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
            }
    
            return new ResponseEntity<>(amount, HttpStatus.OK);
        }
}
