package com.AZDash2.service;

import java.sql.Date;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.ReleaseHistoricalRepository;

@SpringBootTest
public class ReleaseHistoricalServiceTest {

  @Mock private ReleaseHistoricalRepository releaseHistoricalRepository;

  @InjectMocks private ReleaseHistoricalService releaseHistoricalService;

  @Test
  void testGetProgressByDateAndRelease() {
    
    Date date = Date.valueOf("2024-05-16");
    Long idRelease = 1L;
    ReleaseHistorical expectedReleaseHistorical = new ReleaseHistorical();

    // Mockito.when(releaseHistoricalRepository.findByDateBeforeAndIdRelease(date, idRelease))
    //   .thenReturn(Collections.singletonList(expectedReleaseHistorical));


    // Optional<ReleaseHistorical> actualReleaseHistorical =
    //   releaseHistoricalService.getProgressByDateAndRelease(date, idRelease);


    // Assertions.assertEquals(Optional.of(expectedReleaseHistorical), actualReleaseHistorical);
    // Mockito.verify(releaseHistoricalRepository, Mockito.times(1))
    //   .findByDateBeforeAndIdRelease(date, idRelease);
  }
}
