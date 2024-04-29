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
    private String issue_summary;
    private String created_by;
    private String creation_date;
    private String updates; //last comment in jira on given issue
    private String version;
    private String assignee;
    private String environment;




    @ManyToOne
    @JoinColumn(name = "id_release")
    private Release release;

    private Date record_date;

    private Time record_time;

    public Issue(String issue_number, String issue_status, String issue_description,String issue_summary,  String created_by,String creation_date, String updates,String version,String assignee, String environment, Release release, Date record_date,
            Time record_time) {
        this.issue_number = issue_number;
        this.issue_status = issue_status;
        this.issue_description = issue_description;
        this.release = release;
        this.record_date = record_date;
        this.record_time = record_time;
        this.issue_summary = issue_summary;
        this.created_by = created_by;
        this.creation_date = creation_date;
        this.updates = updates;
        this.version = version;
        this.assignee = assignee;
        this.environment = environment;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
        return "Issue [issue_number=" + issue_number + ", issue_status=" + issue_status + ", issue_description="
                + issue_description + ", issue_summary=" + issue_summary + ", created_by=" + created_by
                + ", creation_date=" + creation_date + ", updates=" + updates + ", version=" + version + ", assignee="
                + assignee + ", environment=" + environment + ", release=" + release + ", record_date=" + record_date
                + ", record_time=" + record_time + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((issue_number == null) ? 0 : issue_number.hashCode());
        result = prime * result + ((issue_status == null) ? 0 : issue_status.hashCode());
        result = prime * result + ((issue_description == null) ? 0 : issue_description.hashCode());
        result = prime * result + ((issue_summary == null) ? 0 : issue_summary.hashCode());
        result = prime * result + ((created_by == null) ? 0 : created_by.hashCode());
        result = prime * result + ((creation_date == null) ? 0 : creation_date.hashCode());
        result = prime * result + ((updates == null) ? 0 : updates.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        if (issue_description == null) {
            if (other.issue_description != null)
                return false;
        } else if (!issue_description.equals(other.issue_description))
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
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
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
