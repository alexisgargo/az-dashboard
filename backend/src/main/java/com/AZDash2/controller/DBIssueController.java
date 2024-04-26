package com.AZDash2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.AZDash2.service.DBIssueService;

import jakarta.validation.Valid;

import com.AZDash2.entity.Issue;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("az_dashboard")
public class DBIssueController {

    @Autowired
    DBIssueService issueService;

    @GetMapping("/issues")
    public ResponseEntity<List<Issue>> getIssues() {
        List<Issue> issues = issueService.getIssues();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    // to save issues data
    @PostMapping("/issue")
    public ResponseEntity<Issue> saveIssues(@RequestBody @Valid Issue issues) {
        try{
            issueService.saveIssues(issues);
            return new ResponseEntity<>(issues, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/issues/{date}/{idRelease}")
    public ResponseEntity<List<Issue>> getLatestIssuesByDateAndRelease(@PathVariable("date") Date date,
            @PathVariable("idRelease") Long idRelease) {
        List<Issue> issues;
        try {
            issues = issueService.getLatestIssuesByDateAndRelease(date, idRelease);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/issues/count/{date}/{idRelease}")
    public ResponseEntity<List<Long>> countLatestIssuesByDateAndRelease(@PathVariable("date") Date date,
            @PathVariable("idRelease") Long idRelease) {
        List<Long> count;
         try {
            count = issueService.countLatestIssuesByDateAndRelease(date, idRelease);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
