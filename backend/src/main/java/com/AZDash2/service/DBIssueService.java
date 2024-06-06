package com.AZDash2.service;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AZDash2.entity.Issue;
import com.AZDash2.repository.IssueRepository;
import java.sql.Time;


@Service
public class DBIssueService {


    @Autowired
    IssueRepository issueRepository;

    public List<Issue> getIssues() {
        return issueRepository.findAll();
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
    
}

