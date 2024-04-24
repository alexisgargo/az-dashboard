package com.AZDash2.controller;

import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.AZDash2.service.ReleaseService;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.AdminRepository;
import com.AZDash2.entity.Admin;
import com.AZDash2.repository.EngineerRepository;
import com.AZDash2.entity.Engineer;

@RestController
@RequestMapping("az_dashboard")
public class ReleaseController {
    @Autowired
    ReleaseService releaseService;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EngineerRepository engineerRepository;

    @PostMapping("/release")
    public ResponseEntity<Release> guardarRelease(@Valid @RequestBody Release release) {
        try {
            Optional<Admin> AdminOptional = adminRepository.findById(release.getAdmin().getId_admin());
            Optional<Engineer> EngineerOptional = engineerRepository.findById(release.getEngineer().getId_engineer());

            if (!AdminOptional.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Admin not found");
            }

            if (!EngineerOptional.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Engineer not found");
            }

            release.setAdmin(AdminOptional.get());
            release.setEngineer(EngineerOptional.get());

            Release releaseGuardado = releaseService.saveRelease(release);

            return new ResponseEntity<>(releaseGuardado, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
        }
    }

}
