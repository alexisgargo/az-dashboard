package com.AZDash2.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.AZDash2.service.IssueService;
import com.AZDash2.valueobject.Bug;
import com.AZDash2.valueobject.Issue;
import com.AZDash2.valueobject.TeamProgress;


@RestController
public class IssueController {
    
    Logger logger = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    IssueService issueService;

    /*
     * pull all ISSUES from Jira of given project name and version
     */
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

    /*
     * pull all BUGS from Jira of given project name and version
     */
    @GetMapping("/bugs/{projectIdOrKey}/{versionGiven}")
    public ResponseEntity<List<Bug>> pullBugs(@PathVariable String projectIdOrKey, @PathVariable String versionGiven) {
    List<Bug> bugs;
        try {
            bugs = issueService.getBugs(projectIdOrKey, versionGiven);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("JIRA API failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
        }

        return new ResponseEntity<>(bugs, HttpStatus.OK);
    }
    
    /*
     * pull progress from all tickets of type 'TeamProgress'
     */
    @GetMapping("/progress")
    public ResponseEntity<List<TeamProgress>> pullTeamsProgress() {
    List<TeamProgress> teamProgress;
    
        try {
            teamProgress = issueService.getTeamsProgress();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("Progress JIRA API failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
        }

        return new ResponseEntity<>(teamProgress, HttpStatus.OK);
    }
    
    /*
     * pull progress from all tickets of type 'TeamProgress' and specified version
     */
    @GetMapping("/progress/{version}")
    public ResponseEntity<List<TeamProgress>> pullProgressByVersion(@PathVariable String version) {
    List<TeamProgress> teamProgress;
    
        try {
            teamProgress = issueService.getProgressByVersion(version);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("Progress JIRA API failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
        }

        return new ResponseEntity<>(teamProgress, HttpStatus.OK);
    }
}
