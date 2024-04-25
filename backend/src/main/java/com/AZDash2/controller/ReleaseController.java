package com.AZDash2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AZDash2.entity.Release;
import com.AZDash2.service.ReleaseService;

@RestController
@RequestMapping("az_dashboard")
public class ReleaseController {
    @Autowired
    ReleaseService releaseService;

    @GetMapping("/release/{id}")
    public ResponseEntity<Release> getReleaseById(@PathVariable Long id) {
        Release release;
        try {
            release = releaseService.getReleaseById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(release, HttpStatus.OK);
    }
}
