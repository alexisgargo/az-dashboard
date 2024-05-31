package com.AZDash2.controller;

import com.AZDash2.entity.Admin;
import com.AZDash2.entity.Engineer;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.AdminRepository;
import com.AZDash2.repository.EngineerRepository;
import com.AZDash2.service.ReleaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveReleasesTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private EngineerRepository engineerRepository;

    @Mock
    private ReleaseService releaseService;

    @InjectMocks
    private ReleaseController releaseController;

    private Admin admin;
    private Engineer engineer;
    private Release release;

    @BeforeEach
    public void setup() {
        admin = new Admin();
        admin.setId_admin(1L);

        engineer = new Engineer();
        engineer.setId_engineer(1L);

        release = new Release();
        release.setId_release(1L);
        release.setName("Release Name");
        release.setVersion("1.0");
        release.setAdmin(admin);
        release.setEngineer(engineer);
        release.setCode_cutoff(new Date(System.currentTimeMillis()));
        release.setInit_release_date(new Date(System.currentTimeMillis()));
        release.setCurr_release_date(new Date(System.currentTimeMillis()));
        release.setCreation_date(new Date(System.currentTimeMillis()));
        release.setLast_modification_date(new Date(System.currentTimeMillis()));
        release.setIs_hotfix(true);
        release.setStatus("In Progress");
        release.setIs_rollback(false);
        release.setRelease_note("Initial release note");
    }

    @Test
    public void testSaveRelease_Success() {
        when(adminRepository.findById(admin.getId_admin())).thenReturn(Optional.of(admin));
        when(engineerRepository.findById(engineer.getId_engineer())).thenReturn(Optional.of(engineer));
        when(releaseService.saveRelease(any(Release.class))).thenReturn(release);

        ResponseEntity<Release> response = releaseController.saveRelease(release);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(release, response.getBody());
        verify(adminRepository, times(1)).findById(admin.getId_admin());
        verify(engineerRepository, times(1)).findById(engineer.getId_engineer());
        verify(releaseService, times(1)).saveRelease(release);
    }



    @Test
    public void testSaveRelease_InternalServerError() {
        when(adminRepository.findById(admin.getId_admin())).thenReturn(Optional.of(admin));
        when(engineerRepository.findById(engineer.getId_engineer())).thenReturn(Optional.of(engineer));
        when(releaseService.saveRelease(any(Release.class))).thenThrow(new RuntimeException("Database error"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            releaseController.saveRelease(release);
        });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("An error occurred while processing the request", exception.getReason());
        verify(adminRepository, times(1)).findById(admin.getId_admin());
        verify(engineerRepository, times(1)).findById(engineer.getId_engineer());
        verify(releaseService, times(1)).saveRelease(any(Release.class));
    }
}

