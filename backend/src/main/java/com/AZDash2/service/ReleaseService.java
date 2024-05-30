package com.AZDash2.service;

import com.AZDash2.entity.Release;
import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.ReleaseHistoricalRepository;
import com.AZDash2.repository.ReleaseRepository;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseService {

  @Autowired ReleaseRepository releaseRepository;

  @Autowired ReleaseHistoricalRepository releaseHistoricalRepository;

  public Release getReleaseById(Long idRelease) {
    return releaseRepository.findByIdRelease(idRelease);
  }

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

  public int updateRelease(Long idRelease, Release release) {
    return releaseRepository.updateReleaseById(idRelease, release);
  }

  public List<Release> getReleases() {
    return releaseRepository.findAll();
  }

  public List<Long> getYearMetrics(Date currentDate) {
    int year = currentDate.toLocalDate().getYear();
    String fiscalYearStart = "-09-01";
    String fiscalYearEnd = "-08-31";
    Date startDate, endDate = null;
    if (currentDate.compareTo(Date.valueOf(year + fiscalYearStart)) >= 0) {
      startDate = Date.valueOf(year + fiscalYearStart);
      endDate = Date.valueOf(year + 1 + fiscalYearEnd);
    } else {
      startDate = Date.valueOf(year - 1 + fiscalYearStart);
      endDate = Date.valueOf(year + fiscalYearEnd);
    }
    return List.of(
        releaseRepository.countByIsNotHotfixAndIsNotRollback(startDate, endDate, currentDate),
        releaseRepository.countByIsHotfix(startDate, endDate, currentDate),
        releaseRepository.countByIsRollback(startDate, endDate, currentDate));
  }
}
