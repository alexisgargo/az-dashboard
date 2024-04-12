package com.azdash2.entity;

import org.springframework.stereotype.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issue_number;
    private String issue_status;
    private String issue_description;
    // Getters y setters

    public Issue(Long issue_number, String issue_status, String issue_description) {
        this.issue_number = issue_number;
        this.issue_status = issue_status;
        this.issue_description = issue_description;
    }
    public Issue() {
    }

    public Long getIssueNum() {
        return issue_number;
    }


    public void setIssueNum(Long issue_number) {
        this.issue_number = issue_number;
    }
    public String getStatus() {
        return issue_status;
    }
    public void setStatus(String issue_status) {
        this.issue_status = issue_status;
    }
    public String getDescription() {
        return issue_description;
    }
    public void setDescription(String issue_description) {
        this.issue_description = issue_description;
    }
    
}




