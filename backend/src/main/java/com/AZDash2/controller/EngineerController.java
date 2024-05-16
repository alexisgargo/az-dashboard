package com.AZDash2.controller;

import com.AZDash2.entity.Engineer;
import com.AZDash2.service.EngineerService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("az_dashboard")
public class EngineerController {
  @Autowired EngineerService engineerService;

  @Operation(summary = "Get all the Engineers")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "All Engineers found",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = Engineer.class)))
            }),
        @ApiResponse(responseCode = "404", description = "Engineers not found", content = @Content)
      })
  @GetMapping(value = "/engineers", produces = "application/json")
  public ResponseEntity<List<Engineer>> getEngineers() {
    try {
      List<Engineer> inges = engineerService.getEngineers();

      if (!inges.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(inges, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Save an Engineer")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Engineer created",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Engineer.class))
            }),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content)
      })
  @PostMapping(value = "/engineer", produces = "application/json")
  public ResponseEntity<Engineer> saveEngineer(
      @Parameter(description = "Engineer Name") @RequestBody @Valid Engineer engineer) {
    try {
      engineerService.saveEngineer(engineer);
      return new ResponseEntity<>(engineer, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
