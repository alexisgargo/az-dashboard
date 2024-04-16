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
import com.AZDash2.valueobject.Issue;
import com.AZDash2.valueobject.TeamProgress;


@RestController
public class IssueController {
    
    Logger logger = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    IssueService issueService;

    @GetMapping("/issue/{issueIdOrKey}")
    public ResponseEntity<Issue> testJira(@PathVariable String issueIdOrKey) {
    Issue issue;
        try {
            issue = issueService.getIssue(issueIdOrKey);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("JIRA API failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
        }

        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    /*
     * pull all BUGS or ISSUES from Jira of given project name
     */
    @GetMapping("/project/{type}/{projectIdOrKey}")
    public ResponseEntity<List<Issue>> pullAllBugsOrIssues(@PathVariable String type, @PathVariable String projectIdOrKey) {
    List<Issue> issues;
        try {

            if ("issue".equals(type)){ 
                type = "%20in%20(story%2C%20task)";
            } else if ("bug".equals(type)) {
                type = "%3Dbug";
            };
            
            issues = issueService.getAllBugsOrIssues(type, projectIdOrKey);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("JIRA API failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
        }

        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    /*
     * pull all BUGS or ISSUES from Jira of given project name
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
}
