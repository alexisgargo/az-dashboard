package com.AZDash2.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AZDash2.entity.Issue;
import com.AZDash2.repository.IssueRepository;

@Service
public class DBIssueService {

    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> getAllIssues(String filter) {
        if (filter != null) {
            // Aplica algún filtro si es necesario
        }
        return issueRepository.findAll();
    
    }
}



