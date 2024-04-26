package com.AZDash2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.AZDash2.service.ReleaseHistoricalService;
import com.AZDash2.entity.ReleaseHistorical;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("az_dashboard")
public class ReleaseHistoricalController {
    Logger logger = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    private ReleaseHistoricalService releaseHistoricalService;

    @GetMapping("/historical/{date}/{idRelease}")
    public ResponseEntity<ReleaseHistorical> getIssuesByDateAndRelease(@PathVariable("date") Date date,
            @PathVariable("idRelease") Long idRelease) {
        Optional<ReleaseHistorical> record;
        try {
            record = releaseHistoricalService.getIssuesByDateAndRelease(date, idRelease);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!record.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(record.get(), HttpStatus.OK);
    }

    @GetMapping("/progress/{projectIdOrKey}/{version}")
    public ResponseEntity<ReleaseHistorical> pullProgressByVersion(@PathVariable String version, @PathVariable String projectIdOrKey) {
        ReleaseHistorical teamProgress;
        try {
            teamProgress = releaseHistoricalService.getProgressByVersion(version, projectIdOrKey);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("Progress JIRA API failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
        }

        return new ResponseEntity<>(teamProgress, HttpStatus.OK);
    }
}
