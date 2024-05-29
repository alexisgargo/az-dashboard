package com.AZDash2.controller;

import com.AZDash2.entity.Admin;
import com.AZDash2.entity.Engineer;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.AdminRepository;
import com.AZDash2.repository.EngineerRepository;
import com.AZDash2.service.ReleaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.media.ArraySchema;

@RestController
@RequestMapping("releases")
public class ReleaseController {
  @Autowired
  ReleaseService releaseService;

  @Operation(summary = "Get a specific release given its id", description = "Get a specific release given its id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Release was found", content = {
          @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Release.class))) }),
      @ApiResponse(responseCode = "404", description = "Release not found", content = @Content)
  })
  @GetMapping("/{id}")
  public ResponseEntity<Release> getReleaseById(@PathVariable Long id) {
    Release release;

    release = releaseService.getReleaseById(id);

    if (release == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(release, HttpStatus.OK);
  }

  @Operation(summary = "Get all releases", description = "Get a list of all releases")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Releases were found", content = {
          @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Release.class))) }),
      @ApiResponse(responseCode = "404", description = "Releases were not found")
  })
  @GetMapping("")
  public ResponseEntity<List<Release>> getAllReleases() {
    List<Release> release = releaseService.getReleases();

    if (release.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(release, HttpStatus.OK);
  }

  @Autowired
  AdminRepository adminRepository;

  @Autowired
  EngineerRepository engineerRepository;

  @Operation(summary = "Save a Release")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Release created", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Release.class))
      }),
      @ApiResponse(responseCode = "404", description = "Admin or Engineer not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PostMapping("")
  public ResponseEntity<Release> saveRelease(@Valid @RequestBody Release release) {
    try {
      Optional<Admin> AdminOptional = adminRepository.findById(release.getAdmin().getId_admin());
      Optional<Engineer> EngineerOptional = engineerRepository.findById(release.getEngineer().getId_engineer());

      if (!AdminOptional.isPresent()) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

      if (!EngineerOptional.isPresent()) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

  @Operation(summary = "Update a release by ID using POST", description = "Update a release by its ID using a POST request")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Release was updated", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Release.class)) }),
      @ApiResponse(responseCode = "400", description = "Release or Admin or Engineer not found", content = @Content),
      @ApiResponse(responseCode = "500", description = "An error occurred while processing the request", content = @Content)
  })
  @PutMapping("/{id}")
  public ResponseEntity<Release> updateReleaseById(@PathVariable Long id, @Valid @RequestBody Release releaseToUpdate) {
    try {
      Release existingRelease = releaseService.getReleaseById(id);

      if (existingRelease == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

      // Update engineer only if ID is different
      if (releaseToUpdate.getEngineer() != null && releaseToUpdate.getEngineer().getId_engineer() != null) {
        Optional<Engineer> engineerOptional = engineerRepository
            .findById(releaseToUpdate.getEngineer().getId_engineer());
        if (!engineerOptional.isPresent()) {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        existingRelease.setEngineer(engineerOptional.get());
      }

      if (releaseToUpdate.getName() != null) {
        existingRelease.setName(releaseToUpdate.getName());
      }
      if (releaseToUpdate.getVersion() != null) {
        existingRelease.setVersion(releaseToUpdate.getVersion());
      }
      if (releaseToUpdate.getCode_cutoff() != null) {
        existingRelease.setCode_cutoff(releaseToUpdate.getCode_cutoff());
      }
      if (releaseToUpdate.getInit_release_date() != null) {
        existingRelease.setInit_release_date(releaseToUpdate.getInit_release_date());
      }
      if (releaseToUpdate.getCurr_release_date() != null) {
        existingRelease.setCurr_release_date(releaseToUpdate.getCurr_release_date());
      }
      if (releaseToUpdate.getCreation_date() != null) {
        existingRelease.setCreation_date(releaseToUpdate.getCreation_date());
      }
      if (releaseToUpdate.getLast_modification_date() != null) {
        existingRelease.setLast_modification_date(releaseToUpdate.getLast_modification_date());
      }
      if (releaseToUpdate.isIs_hotfix()) {
        existingRelease.setIs_hotfix(releaseToUpdate.isIs_hotfix());
      }
      if (releaseToUpdate.getStatus() != null) {
        existingRelease.setStatus(releaseToUpdate.getStatus());
      }
      if (releaseToUpdate.isIs_rollback()) {
        existingRelease.setIs_rollback(releaseToUpdate.isIs_rollback());
      }
      if (releaseToUpdate.getRelease_note() != null) {
        existingRelease.setRelease_note(releaseToUpdate.getRelease_note());
      }

      // Update the release using the service method
      int affectedRows = releaseService.updateRelease(id, existingRelease);
      if (affectedRows == 0) {
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
      }

      return new ResponseEntity<>(existingRelease, HttpStatus.OK);
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
    }
  }

  @Operation(summary = "Get metrics of releases of the current fiscal year", description = "Get metrics of releases of the current fiscal year")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Metrics were found", content = {
          @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Release.class))) }),
      @ApiResponse(responseCode = "204", description = "Metrics were not found"),
      @ApiResponse(responseCode = "500", description = "An error occurred while processing the request", content = @Content)
  })
  @GetMapping("/release-yearly-metrics/{currentDate}")
  public ResponseEntity<List<Long>> getYearMetrics(@PathVariable Date currentDate) {
    List<Long> release = releaseService.getYearMetrics(currentDate);

    if (release.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<List<Long>>(release, HttpStatus.OK);
  }
}
