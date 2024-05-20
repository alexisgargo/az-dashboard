package com.AZDash2.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AZDash2.entity.Release;
import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.ReleaseRepository;

@Service
public class ReleaseService {

  @Autowired
  ReleaseRepository releaseRepository;

  @Autowired
  ReleaseHistoricalService releaseHistoricalService;

  public Release getReleaseById(Long idRelease) {
    return releaseRepository.findByIdRelease(idRelease);
  }

  public List<Optional<ReleaseHistorical>> getReleasesByDate(Date date) {
    List<Optional<ReleaseHistorical>> releaseHistoricals = new ArrayList<>();
    List<Long> releases = releaseRepository.findReleasesByReleaseDateAfterAndCreationDateBefore(date);
    for (int release = 0; release < releases.size(); release++) {
      Optional<ReleaseHistorical> releaseHistorical = releaseHistoricalService.getProgressByDateAndRelease(date,
          releases.get(release));
      if (releaseHistorical.isPresent()) {
        releaseHistoricals.add(releaseHistorical);
      }
    }

    return releaseHistoricals;
  }

  public Release saveRelease(Release release) {
    return releaseRepository.save(release);
  }
}
