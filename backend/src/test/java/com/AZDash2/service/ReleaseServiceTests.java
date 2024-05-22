package com.AZDash2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.AZDash2.entity.Admin;
import com.AZDash2.entity.Engineer;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.ReleaseRepository;

@SpringBootTest
public class ReleaseServiceTests {

    @Mock
    ReleaseRepository releaseRepository;

    @InjectMocks
    ReleaseService releaseService;

    @Test
    void testGetReleaseById() {
        // given
        Engineer engineer = new Engineer(1L, "John Doe");
        Date date = Date.valueOf("2021-01-01");
        Admin admin = new Admin(1L, "Jane Doe", "123", date);
        Release expectedRelease = new Release(1L, "AZPRO", "1.0", engineer, admin, date, date,
                date, date, date, true, "In Progress", true, "Release Note");
        given(releaseRepository.findByIdRelease(1L)).willReturn(expectedRelease);

        // when 
        Long idRelease = 1L;
        Release actualRelease = releaseService.getReleaseById(idRelease);

        // then
        assertEquals(expectedRelease, actualRelease);
    }

    @Test
    void testGetReleaseById_NonExistent() {
        // given
        given(releaseRepository.findByIdRelease(999L)).willReturn(null);
    
        // when 
        Long idRelease = 999L;
        Release actualRelease = releaseService.getReleaseById(idRelease);
    
        // then
        assertNull(actualRelease);
    }

    @Test
    void testGetReleaseById_InvalidReleaseId() {
        // when 
        Long idRelease = -1L;
        Release actualRelease = releaseService.getReleaseById(idRelease);
    
        // then
        assertNull(actualRelease);
    }
}