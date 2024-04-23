package com.AZDash2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.AZDash2.service.EngineerService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import com.AZDash2.entity.Engineer;
import java.util.List;

@RestController
@RequestMapping("az_dashboard")
public class EngineerController {
    @Autowired
    EngineerService engineerService;

    @GetMapping("/engineers")
    public ResponseEntity<List<Engineer>> getEngineers() {
        List<Engineer> inges = engineerService.getEngineers();
        return new ResponseEntity<>(inges, HttpStatus.OK);
    }

    @PostMapping("/engineer")
    public ResponseEntity<Engineer> saveEngineer(@RequestBody @Valid Engineer engineer) {
        engineerService.saveEngineer(engineer);
        return new ResponseEntity<>(engineer, HttpStatus.CREATED);
    }

}
