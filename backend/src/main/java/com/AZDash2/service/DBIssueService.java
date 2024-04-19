package com.AZDash2.service;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AZDash2.entity.Issue;
import com.AZDash2.repository.IssueRepository;
import java.sql.Time;

@Service
public class DBIssueService {

    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getLatestIssuesByDateAndRelease(Date date, Long idRelease) {
        Time latestTime = issueRepository.findLatestTimeByDateAndIdRelease(date, idRelease);
        return issueRepository.findByDateAndIdReleaseAndTime(date, idRelease, latestTime);
    }
}



