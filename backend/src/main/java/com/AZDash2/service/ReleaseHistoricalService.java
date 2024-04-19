package com.AZDash2.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.ReleaseHistoricalRepository;

@Service
public class ReleaseHistoricalService {

    @Autowired
    private ReleaseHistoricalRepository releaseHistoricalRepository;

    public Optional<ReleaseHistorical> getIssuesByDateAndRelease(Date date, Long idRelease) {
        return releaseHistoricalRepository.findByDateBeforeAndIdRelease(date, idRelease).stream().findFirst();
    }
}
