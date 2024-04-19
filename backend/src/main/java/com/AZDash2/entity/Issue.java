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

    private Date record_date;

    private Time record_time;

    public Issue(String issue_number, String issue_status, String issue_description, Release release, Date record_date,
            Time record_time) {
        this.issue_number = issue_number;
        this.issue_status = issue_status;
        this.issue_description = issue_description;
        this.release = release;
        this.record_date = record_date;
        this.record_time = record_time;
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

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    public Time getRecord_time() {
        return record_time;
    }

    public void setRecord_time(Time record_time) {
        this.record_time = record_time;
    }

   

}
