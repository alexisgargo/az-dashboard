package com.AZDash2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.sql.Time;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.AZDash2.entity.Issue;
import com.AZDash2.entity.Release;
import com.AZDash2.repository.IssueRepository;

public class DBIssueServiceUnitTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private DBIssueService issueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLatestIssuesByDateAndRelease() {
        Date date = new Date(System.currentTimeMillis());
        Long idRelease = 1L;
        Time latestTime = new Time(System.currentTimeMillis());
        Release release = new Release(); // Assume a default constructor exists

        List<Issue> expectedIssues = Arrays.asList(
            new Issue("4", "Open", "a", "adrian", false, "2024-04-11", "a", "b", "qc", release, date, latestTime, null),
            new Issue("6", "Open", "a", "horacio", false, "2024-04-11", "a", "h", "qc", release, date, latestTime, null),
            new Issue("a", "Closed", "a", "nacho", true, "2024-04-11", "a", "a", "dev", release, date, latestTime, null),
            new Issue("h", "Open", "a", "arturo", true, "2024-04-11", "a", "a", "qc", release, date, latestTime, null),
            new Issue("q", "Open", "a", "arturo", true, "2024-04-11", "a", "a", "qc", release, date, latestTime, null)
        );


        when(issueRepository.findLatestTimeByDateAndIdRelease(any(Date.class), anyLong())).thenReturn(latestTime);
        when(issueRepository.findByDateAndIdReleaseAndTime(any(Date.class), anyLong(), any(Time.class))).thenReturn(expectedIssues);


        List<Issue> actualIssues = issueService.getLatestIssuesByDateAndRelease(date, idRelease);

        assertEquals(expectedIssues, actualIssues);
    }
}
