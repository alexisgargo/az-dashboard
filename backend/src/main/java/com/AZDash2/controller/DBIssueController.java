package com.azdash2.controller;
import java.io.Console;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.azdash2.service.DBIssueService;
import com.azdash2.entity.Issue;

@RestController
public class DBIssueController {

    @Autowired
    private DBIssueService issueService;

    @GetMapping("/issues")
public ResponseEntity<List<Issue>> getAllIssues() {
    List<Issue> issues;
    try {
        issues = issueService.getAllIssues(null); 
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
   
    return new ResponseEntity<>(issues, HttpStatus.OK);
}

}
