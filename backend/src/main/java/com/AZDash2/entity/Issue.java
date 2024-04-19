package com.AZDash2.entity;

import java.sql.Date;
import java.sql.Time;
import jakarta.persistence.*;

@Entity
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String issue_number;
    private String issue_status;
    private String issue_description;

    @ManyToOne
    @JoinColumn(name = "id_release")
    private Release release;

    @Column(name = "record_date")
    private Date recordDate;

    @Column(name = "record_time")
    private Time recordTime;

    public Issue(String issue_number, String issue_status, String issue_description, Release release, Date recordDate,
            Time recordTime) {
        this.issue_number = issue_number;
        this.issue_status = issue_status;
        this.issue_description = issue_description;
        this.release = release;
        this.recordDate = recordDate;
        this.recordTime = recordTime;
    }

    public Issue() {
    }

    public String getIssue_number() {
        return issue_number;
    }

    public void setIssue_number(String issue_number) {
        this.issue_number = issue_number;
    }

    public String getIssue_status() {
        return issue_status;
    }

    public void setIssue_status(String issue_status) {
        this.issue_status = issue_status;
    }

    public String getIssue_description() {
        return issue_description;
    }

    public void setIssue_description(String issue_description) {
        this.issue_description = issue_description;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Time getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Time recordTime) {
        this.recordTime = recordTime;
    }

}
