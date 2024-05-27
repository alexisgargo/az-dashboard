package com.AZDash2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AZDash2.entity.Release;
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

  public Release saveRelease(Release release) {
    return releaseRepository.save(release);
  }

  public int updateRelease(Long idRelease, Release release) {
    return releaseRepository.updateReleaseById(idRelease, release);
  }

  public List<Release> getReleases() {
    return releaseRepository.findAll();
  }

}
