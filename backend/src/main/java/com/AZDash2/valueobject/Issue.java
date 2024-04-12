package com.azdash2.valueobject;

public class Issue {
    private String key;
    private String status;
    private String description;
    private String author;
    private String summary;
    private String assignee;
    


    public Issue() {}

    public Issue(String key, String status, String description, String author, String summary, String assignee) {
        this.key = key;
        this.status = status;
        this.description = description;
        this.author = author;
        this.summary = summary;
        this.assignee = assignee;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
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
}

