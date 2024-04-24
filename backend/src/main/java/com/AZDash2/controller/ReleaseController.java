package com.AZDash2.controller;

import java.net.URI;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.AZDash2.service.ReleaseService;
import com.AZDash2.repository.ReleaseRepository;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.AdminRepository;
import com.AZDash2.entity.Admin;
import com.AZDash2.repository.EngineerRepository;
import com.AZDash2.entity.Engineer;
import com.AZDash2.entity.DTO.AdminDTO;


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

            AdminDTO adminDTO = new AdminDTO(releaseGuardado.getAdmin());
            releaseGuardado.setAdmin(adminDTO);

            return new ResponseEntity<>(releaseGuardado, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
        }
    }

}
