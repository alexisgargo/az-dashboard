package com.AZDash2.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

    public void saveIssues(Issue issue) {
        issueRepository.save(issue);
    }

    public List<Issue> getLatestIssuesByDateAndRelease(Date date, Long idRelease) {
        Time latestTime = issueRepository.findLatestTimeByDateAndIdRelease(date, idRelease);
        return issueRepository.findByDateAndIdReleaseAndTime(date, idRelease, latestTime);
    }

    public List<Long> countLatestIssuesByDateAndRelease(Date date, Long idRelease) {
        Time latestTime = issueRepository.findLatestTimeByDateAndIdRelease(date, idRelease);
        List<Long> counts = new ArrayList<>();
        long ticketCount = issueRepository.countTicketByDateAndIdReleaseAndTime(date, idRelease, latestTime);
        counts.add(ticketCount);
        long bugCount = issueRepository.countBugByDateAndIdReleaseAndTime(date, idRelease, latestTime);
        counts.add(bugCount);

        return counts;
    }

}
