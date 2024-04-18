package com.AZDash2.entity;
import java.sql.Date;
import org.springframework.stereotype.Repository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String issue_number;
    private String issue_status;
    private String issue_description;
    private long idRelease;
    private Date date;
    // Getters y setters

    public Issue(String issue_number, String issue_status, String issue_description,Date date,long idRelease) {
        this.issue_number = issue_number;
        this.issue_status = issue_status;
        this.issue_description = issue_description;
        this.idRelease =idRelease;
        this.date = date; 
    }

    public Issue() {
    }

    public String getIssueNum() {
        return issue_number;
    }

    public void setIssueNum(String issue_number) {
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
    public Long getIdRelease() {
        return idRelease;
    }

    public void setIdRelease(Long idRelease) {
        this.idRelease = idRelease;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
