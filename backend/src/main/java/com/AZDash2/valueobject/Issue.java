package com.AZDash2.valueobject;

public class Issue {
    private String key;
    private String status;
    private String description;
    private String creator;
    private String summary;
    private String assignee;
    private String created;
    private String resolved;


    public Issue() {}

    public Issue(String key, String status, String description, String creator, String summary, String assignee, String created, String resolved) {
        this.key = key;
        this.status = status;
        this.description = description;
        this.creator = creator;
        this.summary = summary;
        this.assignee = assignee;
        this.created = created;
        this.resolved = resolved;

    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }
}

