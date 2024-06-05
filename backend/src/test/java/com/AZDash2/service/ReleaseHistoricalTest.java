
package com.AZDash2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

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
import com.AZDash2.repository.ReleaseHistoricalRepository;

@SpringBootTest
public class ReleaseHistoricalTest {

  @Mock
  ReleaseHistoricalRepository releaseHistoricalRepository;

  @InjectMocks
  ReleaseHistoricalService releaseHistoricalService;

  @Test
  void testGetReleasesByDate() {
    // given
    Date date = Date.valueOf("2024-05-10");
    Engineer engineer1 = new Engineer(1L, "Engineer Name");
    Engineer engineer2 = new Engineer(2L, "Engineer Name 2");

    Admin admin1 = new Admin(1L, "Admin Name", "abcd1234",
        Date.valueOf("2024-05-01"));
    Admin admin2 = new Admin(2L, "Admin Name 2", "1234abcd",
        Date.valueOf("2024-05-01"));

    Release release1 = new Release(1L, "nameExample", "labelExample", engineer2,
        admin1, Date.valueOf("2024-05-01"),
        Date.valueOf("2024-05-25"), Date.valueOf("2024-05-25"),
        Date.valueOf("2024-05-01"), Date.valueOf("2024-05-01"),
        true, "delayed", false, "noteExample");
    Release release2 = new Release(2L, "nameExample2", "labelExample2",
        engineer1, admin2, Date.valueOf("2024-04-25"),
        Date.valueOf("2024-05-15"), Date.valueOf("2024-05-15"),
        Date.valueOf("2024-04-25"), Date.valueOf("2024-04-25"),
        false, "on track", true, "noteExample2");
    List<Release> releases = List.of(release1, release2);

    ReleaseHistorical releaseHistorical1 = new ReleaseHistorical(3L, release1,
        Date.valueOf("2024-05-03"),
        Time.valueOf("20:20:20"), new BigDecimal(100), new BigDecimal(100), new BigDecimal(100),
        new BigDecimal(100));
    ReleaseHistorical releaseHistorical2 = new ReleaseHistorical(6L, release2,
        Date.valueOf("2024-04-28"),
        Time.valueOf("18:20:20"), new BigDecimal(90), new BigDecimal(100), new BigDecimal(100),
        new BigDecimal(70));
    List<Optional<ReleaseHistorical>> expectedReleases = List.of(Optional.of(releaseHistorical1),
        Optional.of(releaseHistorical2));
    given(releaseHistoricalRepository.findDistinctReleaseIds()).willReturn(
        releases);
    given(releaseHistoricalRepository.findByDateBeforeAndReleaseIdOrderByRecordDateDescRecordTimeDesc(any(Date.class),
        eq(1L)))
        .willReturn(List.of(releaseHistorical1));
    given(releaseHistoricalRepository.findByDateBeforeAndReleaseIdOrderByRecordDateDescRecordTimeDesc(any(Date.class),
        eq(2L)))
        .willReturn(List.of(releaseHistorical2));

    // when
    List<Optional<ReleaseHistorical>> actualReleases = releaseHistoricalService.getByDate(date);

    // then
    assertEquals(2L, actualReleases.size());
    assertEquals(expectedReleases.get(0), actualReleases.get(0));
    assertEquals(expectedReleases.get(1), actualReleases.get(1));
  }

  @Test
  void testGetReleasesByDate_NonExistent() {
    // given
    given(releaseHistoricalRepository.findDistinctReleaseIds()).willReturn(new ArrayList<Release>());
    // when
    List<Optional<ReleaseHistorical>> actualReleases = releaseHistoricalService.getByDate(new Date(0));

    // then
    assertEquals(new ArrayList<Release>(), actualReleases);
  }
}
