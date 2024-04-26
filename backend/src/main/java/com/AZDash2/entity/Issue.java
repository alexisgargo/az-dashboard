package com.AZDash2.entity;

import java.sql.Date;
import java.sql.Time;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotNull(message = "Issue number is mandatory")
    @Size(min = 1, max = 50, message = "Issue number must be between 1 and 50 characters")
    private String issue_number;

    @NotNull(message = "Issue status is mandatory")
    @Size(min = 1, max = 50, message = "Issue status must be between 1 and 50 characters")
    private String issue_status;

    @NotNull(message = "Issue summary is mandatory")
    @Size(min = 1, max = 140, message = "Issue summary must be between 1 and 140 characters")
    private String issue_summary;

    @NotNull(message = "Created by is mandatory")
    @Size(min = 1, max = 50, message = "Created by must be between 1 and 50 characters")
    private String created_by;

    @NotNull(message = "Feature is mandatory")
    @Column(columnDefinition = "BIT(1)")
    private boolean is_feature;

    @NotNull(message = "Creation date is mandatory")
    @Size(min = 1, max = 50, message = "Creation date must be between 1 and 50 characters")
    private String creation_date;

    @Size(min = 1, max = 140, message = "Updates must be between 1 and 140 characters")
    private String updates; // last comment in jira on given issue

    @NotNull(message = "Assignee is mandatory")
    @Size(min = 1, max = 50, message = "Assignee must be between 1 and 50 characters")
    private String assignee;

    @NotNull(message = "Environment is mandatory")
    @Size(min = 1, max = 50, message = "Environment must be between 1 and 50 characters")
    private String environment;

    @ManyToOne
    @JoinColumn(name = "id_release")
    @NotNull(message = "Release is mandatory")
    private Release release;

    @NotNull(message = "Record date is mandatory")
    @PastOrPresent(message = "Record date must be in the past or present")
    private Date record_date;

    @NotNull(message = "Record time is mandatory")
    @PastOrPresent(message = "Record time must be in the past or present")
    private Time record_time;

    private String close_date;

    public Issue(
            @NotNull(message = "Issue number is mandatory") @Size(min = 1, max = 50, message = "Issue number must be between 1 and 50 characters") String issue_number,
            @NotNull(message = "Issue status is mandatory") @Size(min = 1, max = 50, message = "Issue status must be between 1 and 50 characters") String issue_status,
            @NotNull(message = "Issue summary is mandatory") @Size(min = 1, max = 140, message = "Issue summary must be between 1 and 140 characters") String issue_summary,
            @NotNull(message = "Created by is mandatory") @Size(min = 1, max = 50, message = "Created by must be between 1 and 50 characters") String created_by,
            @NotNull(message = "Feature is mandatory") boolean is_feature,
            @NotNull(message = "Creation date is mandatory") @Size(min = 1, max = 50, message = "Creation date must be between 1 and 50 characters") String creation_date,
            @Size(min = 1, max = 140, message = "Updates must be between 1 and 140 characters") String updates,
            @NotNull(message = "Assignee is mandatory") @Size(min = 1, max = 50, message = "Assignee must be between 1 and 50 characters") String assignee,
            @NotNull(message = "Environment is mandatory") @Size(min = 1, max = 50, message = "Environment must be between 1 and 50 characters") String environment,
            @NotNull(message = "Release is mandatory") Release release,
            @NotNull(message = "Record date is mandatory") @PastOrPresent(message = "Record date must be in the past or present") Date record_date,
            @NotNull(message = "Record time is mandatory") @PastOrPresent(message = "Record time must be in the past or present") Time record_time,
            String close_date) {
        this.issue_number = issue_number;
        this.issue_status = issue_status;
        this.issue_summary = issue_summary;
        this.created_by = created_by;
        this.is_feature = is_feature;
        this.creation_date = creation_date;
        this.updates = updates;
        this.assignee = assignee;
        this.environment = environment;
        this.release = release;
        this.record_date = record_date;
        this.record_time = record_time;
        this.is_feature = is_feature;
        this.close_date = close_date;

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

    public String getIssue_summary() {
        return issue_summary;
    }

    public void setIssue_summary(String issue_summary) {
        this.issue_summary = issue_summary;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public boolean isIs_feature() {
        return is_feature;
    }

    public void setIs_feature(boolean is_feature) {
        this.is_feature = is_feature;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getUpdates() {
        return updates;
    }

    public void setUpdates(String updates) {
        this.updates = updates;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
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


    public boolean getFeature() {
        return is_feature;
    }

    public void setFeature(boolean is_feature) {
        this.is_feature = is_feature;
    }

    public String getCloseDate() {
        return close_date;
    }

    public void setCloseDate(String environment) {
        this.environment = environment;
    }
}
