package com.AZDash2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.AZDash2.entity.Admin;
import com.AZDash2.entity.Engineer;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.AdminRepository;
import com.AZDash2.repository.EngineerRepository;
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

  @Test
  void testGetAllReleases() {
    // given
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

    List<Release> expectedReleases = List.of(release1, release2);
    given(releaseRepository.findAll()).willReturn(expectedReleases);

    // when
    List<Release> actualReleases = releaseService.getReleases();

    // then
    assertEquals(2L, actualReleases.size());
    assertEquals(expectedReleases.get(0), actualReleases.get(0));
    assertEquals(expectedReleases.get(1), actualReleases.get(1));
  }

  @Mock
  AdminRepository adminRepository;

  @Mock
  EngineerRepository engineerRepository;

  @Test
  void testSaveRelease() {
    // given
    Engineer engineer = new Engineer(1L, "John Doe");
    Admin admin = new Admin(1L, "Jane Doe", "123", null); // Assuming no specific date is required for admin in this
                                                          // test
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
