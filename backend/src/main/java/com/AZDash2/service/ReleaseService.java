package com.AZDash2.service;

import com.AZDash2.entity.Release;
import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.ReleaseHistoricalRepository;
import com.AZDash2.repository.ReleaseRepository;
import java.math.BigDecimal;
import java.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseService {

  @Autowired ReleaseRepository releaseRepository;

  @Autowired ReleaseHistoricalRepository releaseHistoricalRepository;

  public Release getReleaseById(Long idRelease) {
    return releaseRepository.findByIdRelease(idRelease);
  }

  // private final ReleaseRepository releaseRepository;

  // @Autowired
  // public ReleaseService(ReleaseRepository releaseRepository) {
  //     this.releaseRepository = releaseRepository;
  // }

  public Release saveRelease(Release release) {
    Release savedRelease = releaseRepository.save(release);

    ReleaseHistorical releaseHistorical = new ReleaseHistorical();
    releaseHistorical.setRelease(savedRelease);
    releaseHistorical.setPercent_qa(BigDecimal.ZERO);
    releaseHistorical.setPercent_uat(BigDecimal.ZERO);
    releaseHistorical.setPercent_third_party(BigDecimal.ZERO);
    releaseHistorical.setPercent_pt(BigDecimal.ZERO);

    Date currentDate = new Date(System.currentTimeMillis());
    Time currentTime = new Time(System.currentTimeMillis());
    releaseHistorical.setRecordDate(currentDate);
    releaseHistorical.setRecordTime(currentTime);

    releaseHistoricalRepository.save(releaseHistorical);

    return savedRelease;
  }
}
