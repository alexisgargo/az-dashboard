package com.AZDash2.entity;

import java.sql.Date;
import java.sql.Time;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "issues")
public class Issue {
    @Schema(description = "Issue ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_issue;

    @NotNull(message = "Issue number is mandatory")
    @Size(min = 1, max = 50, message = "Issue number must be between 1 and 50 characters")
    private String issue_number;

    @Schema(description = "Issue status")
    @NotNull(message = "Issue status is mandatory")
    @Size(min = 1, max = 50, message = "Issue status must be between 1 and 50 characters")
    private String issue_status;

    @Schema(description = "Issue summary")
    @NotNull(message = "Issue summary is mandatory")
    @Size(min = 1, max = 140, message = "Issue summary must be between 1 and 140 characters")
    private String issue_summary;

    @Schema(description = "Issue creator")
    @NotNull(message = "Created by is mandatory")
    @Size(min = 1, max = 50, message = "Created by must be between 1 and 50 characters")
    private String created_by;

    @Schema(description = "Is it a feature or not")
    @NotNull(message = "Feature is mandatory")
    @Column(columnDefinition = "BIT(1)")
    private boolean is_feature;

    @Schema(description = "Date of Issue creation")
    @NotNull(message = "Creation date is mandatory")
    @Size(min = 1, max = 50, message = "Creation date must be between 1 and 50 characters")
    private String creation_date;

    @Schema(description = "Last comment in Jira on this issue")
    @Size(min = 1, max = 140, message = "Updates must be between 1 and 140 characters")
    private String updates; // last comment in jira on given issue

    @Schema(description = "Who is working on this issue")
    @NotNull(message = "Assignee is mandatory")
    @Size(min = 1, max = 50, message = "Assignee must be between 1 and 50 characters")
    private String assignee;

    @Schema(description = "Where was this issue found")
    @NotNull(message = "Environment is mandatory")
    @Size(min = 1, max = 50, message = "Environment must be between 1 and 50 characters")
    private String environment;
    
    @Schema(description = "Issue belongs to this release")
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    private String title;
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private String description;

    public Issue(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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


    @Override
    public String toString() {
        return "Issue [issue_number=" + issue_number + ", issue_status=" + issue_status + ", issue_summary=" + issue_summary + ", created_by=" + created_by
                + ", creation_date=" + creation_date + ", updates=" + updates + ", assignee="
                + assignee + ", environment=" + environment + ", release=" + release + ", record_date=" + record_date
                + ", record_time=" + record_time + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((issue_number == null) ? 0 : issue_number.hashCode());
        result = prime * result + ((issue_status == null) ? 0 : issue_status.hashCode());
        result = prime * result + ((issue_summary == null) ? 0 : issue_summary.hashCode());
        result = prime * result + ((created_by == null) ? 0 : created_by.hashCode());
        result = prime * result + ((creation_date == null) ? 0 : creation_date.hashCode());
        result = prime * result + ((updates == null) ? 0 : updates.hashCode());
        result = prime * result + ((assignee == null) ? 0 : assignee.hashCode());
        result = prime * result + ((environment == null) ? 0 : environment.hashCode());
        result = prime * result + ((release == null) ? 0 : release.hashCode());
        result = prime * result + ((record_date == null) ? 0 : record_date.hashCode());
        result = prime * result + ((record_time == null) ? 0 : record_time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Issue other = (Issue) obj;
        if (issue_number == null) {
            if (other.issue_number != null)
                return false;
        } else if (!issue_number.equals(other.issue_number))
            return false;
        if (issue_status == null) {
            if (other.issue_status != null)
                return false;
        } else if (!issue_status.equals(other.issue_status))
            return false;
        if (issue_summary == null) {
            if (other.issue_summary != null)
                return false;
        } else if (!issue_summary.equals(other.issue_summary))
            return false;
        if (created_by == null) {
            if (other.created_by != null)
                return false;
        } else if (!created_by.equals(other.created_by))
            return false;
        if (creation_date == null) {
            if (other.creation_date != null)
                return false;
        } else if (!creation_date.equals(other.creation_date))
            return false;
        if (updates == null) {
            if (other.updates != null)
                return false;
        } else if (!updates.equals(other.updates))
            return false;
        if (assignee == null) {
            if (other.assignee != null)
                return false;
        } else if (!assignee.equals(other.assignee))
            return false;
        if (environment == null) {
            if (other.environment != null)
                return false;
        } else if (!environment.equals(other.environment))
            return false;
        if (release == null) {
            if (other.release != null)
                return false;
        } else if (!release.equals(other.release))
            return false;
        if (record_date == null) {
            if (other.record_date != null)
                return false;
        } else if (!record_date.equals(other.record_date))
            return false;
        if (record_time == null) {
            if (other.record_time != null)
                return false;
        } else if (!record_time.equals(other.record_time))
            return false;
        return true;
    }

    
}
