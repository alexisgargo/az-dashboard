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
import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("az_dashboard")
public class ReleaseHistoricalController {

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
}
