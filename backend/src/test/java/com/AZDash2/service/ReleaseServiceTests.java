package com.AZDash2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoInteractions;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.AZDash2.entity.Admin;
import com.AZDash2.entity.Engineer;
import com.AZDash2.entity.Release;
import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.AdminRepository;
import com.AZDash2.repository.EngineerRepository;
import com.AZDash2.repository.ReleaseRepository;

@SpringBootTest
public class ReleaseServiceTests {

  @Mock
  ReleaseRepository releaseRepository;

  @Mock
  ReleaseHistoricalService releaseHistoricalService;

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

  @Test
  void testGetReleasesByDate() {
    // given
    Date date = Date.valueOf("2024-05-10");
    List<Long> releases = List.of(1L, 2L);
    Engineer engineer1 = new Engineer(1L, "Engineer Name");
    Engineer engineer2 = new Engineer(2L, "Engineer Name 2");
    Admin admin1 = new Admin(1L, "Admin Name", "abcd1234", Date.valueOf("2024-05-01"));
    Admin admin2 = new Admin(2L, "Admin Name 2", "1234abcd", Date.valueOf("2024-05-01"));
    Release release1 = new Release(1L, "nameExample", "labelExample", engineer2, admin1, Date.valueOf("2024-05-01"),
        Date.valueOf("2024-05-01"), Date.valueOf("2024-05-25"), Date.valueOf("2024-05-25"), Date.valueOf("2024-05-01"),
        true, "delayed", false, "noteExample");
    Release release2 = new Release(2L, "nameExample2", "labelExample2", engineer1, admin2, Date.valueOf("2024-04-25"),
        Date.valueOf("2024-04-25"), Date.valueOf("2024-05-15"), Date.valueOf("2024-05-15"), Date.valueOf("2024-04-25"),
        false, "on track", true, "noteExample2");
    ReleaseHistorical releaseHistorical1 = new ReleaseHistorical(3L, release1, Date.valueOf("2024-05-03"),
        Time.valueOf("20:20:20"), new BigDecimal(100), new BigDecimal(100), new BigDecimal(100),
        new BigDecimal(100));
    ReleaseHistorical releaseHistorical2 = new ReleaseHistorical(6L, release2, Date.valueOf("2024-04-28"),
        Time.valueOf("18:20:20"), new BigDecimal(90), new BigDecimal(100), new BigDecimal(100),
        new BigDecimal(70));
    List<Optional<ReleaseHistorical>> expectedReleases = List.of(Optional.of(releaseHistorical1),
        Optional.of(releaseHistorical2));
    given(releaseRepository.findReleasesByDateAfter(any(Date.class))).willReturn(releases);
    given(releaseHistoricalService.getProgressByDateAndRelease(any(Date.class), eq(1L)))
        .willReturn(Optional.of(releaseHistorical1));
    given(releaseHistoricalService.getProgressByDateAndRelease(any(Date.class), eq(2L)))
        .willReturn(Optional.of(releaseHistorical2));

    // when
    List<Optional<ReleaseHistorical>> actualReleases = releaseService.getReleasesByDate(date);

    // then
    assertEquals(2L, actualReleases.size());
    assertEquals(expectedReleases.get(0), actualReleases.get(0));
    assertEquals(expectedReleases.get(1), actualReleases.get(1));
  }

  @Test
  void testGetReleasesByDate_NonExistent() {
    // given
    given(releaseRepository.findReleasesByDateAfter(any(Date.class))).willReturn(new ArrayList<Long>());
    // when
    List<Optional<ReleaseHistorical>> actualReleases = releaseService.getReleasesByDate(new Date(0));

    // then
    assertEquals(new ArrayList<Long>(), actualReleases);
  }

  @Mock
  AdminRepository adminRepository;

  @Mock
  EngineerRepository engineerRepository;

  @Test
  void testSaveRelease() {
    // given
    Engineer engineer = new Engineer(1L, "John Doe");
    Admin admin = new Admin(1L, "Jane Doe", "123", null); // Assuming no specific date is required for admin in this test
    Release release = new Release(1L, "AZPRO", "1.0", engineer, admin, null, null, null, null, null, true,
        "In Progress", true, "Release Note");
    given(adminRepository.findById(admin.getId_admin())).willReturn(Optional.of(admin));
    given(engineerRepository.findById(engineer.getId_engineer())).willReturn(Optional.of(engineer));
    given(releaseRepository.save(any(Release.class))).willReturn(release);

    // when
    Release savedRelease = releaseService.saveRelease(release);

    // then
    assertEquals(release, savedRelease);
  }

  @Test
  void testUpdateRelease() {
      // Given
      Long idRelease = 1L;
      Release existingRelease = new Release();
      existingRelease.setName("Old Name");
      existingRelease.setVersion("Old Version");
      existingRelease.setInit_release_date(Date.valueOf("2022-01-01"));
      existingRelease.setCurr_release_date(Date.valueOf("2022-01-01"));
      existingRelease.setCreation_date(Date.valueOf("2022-01-01"));
      existingRelease.setLast_modification_date(Date.valueOf("2022-01-01"));
      existingRelease.setIs_hotfix(false);
      existingRelease.setStatus("Old Status");
      existingRelease.setIs_rollback(false);
      existingRelease.setRelease_note("Old Release Note");

      Release updatedRelease = new Release();
      updatedRelease.setName("New Name");
      updatedRelease.setVersion("New Version");
      updatedRelease.setInit_release_date(Date.valueOf("2023-01-01"));
      updatedRelease.setCurr_release_date(Date.valueOf("2023-01-01"));
      updatedRelease.setCreation_date(Date.valueOf("2023-01-01"));
      updatedRelease.setLast_modification_date(Date.valueOf("2023-01-01"));
      updatedRelease.setIs_hotfix(true);
      updatedRelease.setStatus("New Status");
      updatedRelease.setIs_rollback(true);
      updatedRelease.setRelease_note("New Release Note");

      // Mock the repository method
      when(releaseRepository.updateReleaseById(any(Long.class), any(Release.class))).thenReturn(1);

      // When
      int affectedRows = releaseService.updateRelease(idRelease, updatedRelease);

      // Then
      assertEquals(1, affectedRows);
      verify(releaseRepository).updateReleaseById(idRelease, updatedRelease);
  }

}
