package com.AZDash2.controller;

import com.AZDash2.entity.Admin;
import com.AZDash2.entity.Engineer;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.AdminRepository;
import com.AZDash2.repository.EngineerRepository;
import com.AZDash2.service.ReleaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("az_dashboard")
public class ReleaseController {
  @Autowired ReleaseService releaseService;

  @Operation(
      summary = "Get a specific release given its id",
      description = "Get a specific release given its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Release was found",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = Release.class)))
            }),
        @ApiResponse(responseCode = "404", description = "Release not found", content = @Content)
      })
  @GetMapping("/release/{id}")
  public ResponseEntity<Release> getReleaseById(@PathVariable Long id) {
    Release release = releaseService.getReleaseById(id);
    if (release != null) {
      return new ResponseEntity<>(release, HttpStatus.OK);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Release not found");
    }
  }

  @Autowired AdminRepository adminRepository;

  @Autowired EngineerRepository engineerRepository;

  @Operation(summary = "Save a Release")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Release created",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Release.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Admin or Engineer not found",
            content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content)
      })
  @PostMapping("/release")
  public ResponseEntity<Release> saveRelease(@Valid @RequestBody Release release) {
    try {
      Optional<Admin> AdminOptional = adminRepository.findById(release.getAdmin().getId_admin());
      Optional<Engineer> EngineerOptional =
          engineerRepository.findById(release.getEngineer().getId_engineer());

      if (!AdminOptional.isPresent()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
      }

      if (!EngineerOptional.isPresent()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Engineer not found");
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
