package com.AZDash2.service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AZDash2.entity.Issue;
import com.AZDash2.entity.Release;
import com.AZDash2.entity.ReleaseHistorical;
import com.AZDash2.repository.IssueRepository;
import com.AZDash2.repository.ReleaseHistoricalRepository;
import com.AZDash2.repository.ReleaseRepository;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


@Service
public class DBIssueService {

    private static final Logger logger = LoggerFactory.getLogger(DBIssueService.class);
    private ReleaseRepository releaseRepository;
    private ReleaseHistoricalRepository releaseHistoricalRepository;
    private ReleaseHistoricalService releaseHistoricalService;

    @Autowired
    IssueRepository issueRepository;

    public List<Issue> getIssues() {
        return issueRepository.findAll();
    }

    public void saveIssues(Issue issue) {
        issueRepository.save(issue);
    }

    public List<Issue> getLatestIssuesByDateAndRelease(Date date, Long idRelease) {
        Time latestTime = issueRepository.findLatestTimeByDateAndIdRelease(date, idRelease);
        return issueRepository.findByDateAndIdReleaseAndTime(date, idRelease, latestTime);
    }

    public Map<String, Long> countLatestIssuesByDateAndRelease(Date date, Long idRelease) {
        Time latestTime = issueRepository.findLatestTimeByDateAndIdRelease(date, idRelease);
        Map<String, Long> counts = new HashMap<>();
    
        long ticketCount = issueRepository.countTicketByDateAndIdReleaseAndTime(date, idRelease, latestTime);
        counts.put("issues", ticketCount);
    
        long bugCount = issueRepository.countBugByDateAndIdReleaseAndTime(date, idRelease, latestTime);
        counts.put("bugs", bugCount);
    
        return counts;
    }
    @Autowired
    public void SaveTeamPService(ReleaseRepository releaseRepository, 
                            ReleaseHistoricalRepository releaseHistoricalRepository, 
                            ReleaseHistoricalService releaseHistoricalService) {
        this.releaseRepository = releaseRepository;
        this.releaseHistoricalRepository = releaseHistoricalRepository;
        this.releaseHistoricalService = releaseHistoricalService;
    }
    public List<ReleaseHistorical> getAndSaveProgressReleases(String projectIdOrKey) throws URISyntaxException, IOException, InterruptedException {
        List<Release> releases = releaseRepository.findByStatus("progress");
        List<ReleaseHistorical> progressReleases = new ArrayList<>();

        for (Release release : releases) {
            String version = release.getversion();
            try {
                ReleaseHistorical teamProgress = releaseHistoricalService.getProgressByVersion(version, release.getName());
                if (teamProgress != null) {
                    teamProgress.setRecordDate(Date.valueOf(LocalDate.now()));
                    teamProgress.setRecordTime(Time.valueOf(LocalTime.now()));
                    teamProgress.setRelease(release);  

                    releaseHistoricalRepository.save(teamProgress);
                    progressReleases.add(teamProgress);
                } else {
                    logger.error("No progress data found for version: {} and name: {}", version, release.getName());
                }
            } catch (Exception e) {
                logger.error("Error saving progress for version: {} and name: {}", version, release.getName(), e);
            }
        }
        return progressReleases;
    }
}

