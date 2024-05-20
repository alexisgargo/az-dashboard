package com.AZDash2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.AZDash2.entity.Admin;
import com.AZDash2.entity.Engineer;
import com.AZDash2.entity.Release;
import com.AZDash2.service.ReleaseService;
import java.sql.Date;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ReleaseControllerTest {

  @Mock private ReleaseService releaseService;

  @InjectMocks private ReleaseController releaseController;

  private Release createMockRelease(Long id) {
    Engineer engineer = new Engineer(id, "Alejandro Robles");
    Admin admin = createMockAdmin(1L);

    long now = System.currentTimeMillis();
    Date sqlDate = new Date(now);

    return new Release(
        id,
        "Nombre del Release",
        "v1.0",
        engineer,
        admin,
        sqlDate, // code_cutoff
        sqlDate, // init_release_date
        sqlDate, // curr_release_date
        sqlDate, // creation_date
        sqlDate, // last_modification_date
        true,
        "Activo", 
        false, 
        "Nota del release"
        );
  }

  private Admin createMockAdmin(Long id) {
    Date sqlDate = new Date(System.currentTimeMillis());
    return new Admin(
        id, "Nombre del Admin", "ContraseñaSegura123", sqlDate // creation_date
        );
  }

  @Test
  public void getReleaseByIdTest_Found() throws Exception {
    Long id = 1L;
    Release mockRelease = createMockRelease(id);

    when(releaseService.getReleaseById(id)).thenReturn(mockRelease);

    ResponseEntity<Release> response = releaseController.getReleaseById(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(mockRelease, response.getBody());
  }

  @Test
  public void getReleaseByIdTest_NotFound() throws Exception {
    Long id = 2L;
    when(releaseService.getReleaseById(id)).thenThrow(new NoSuchElementException("Release not found"));

    ResponseEntity<Release> response = releaseController.getReleaseById(id);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void getReleaseByIdTest_InternalServerError() throws Exception {
    Long id = 3L;
    when(releaseService.getReleaseById(id))
        .thenThrow(new RuntimeException("Internal server error"));

    ResponseEntity<Release> response = releaseController.getReleaseById(id);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }
}
