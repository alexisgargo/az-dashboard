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
    private String comment;

    public Issue() {}

    public Issue(String key, String status, String description, String creator, String summary, String assignee, String created, String resolved, String comment) {
        this.key = key;
        this.status = status;
        this.description = description;
        this.creator = creator;
        this.summary = summary;
        this.assignee = assignee;
        this.created = created;
        this.resolved = resolved;
        this.comment = comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Issue [key=" + key + ", status=" + status + ", description=" + description + ", creator=" + creator
                + ", summary=" + summary + ", assignee=" + assignee + ", created=" + created + ", resolved=" + resolved
                + ", comment=" + comment + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((creator == null) ? 0 : creator.hashCode());
        result = prime * result + ((summary == null) ? 0 : summary.hashCode());
        result = prime * result + ((assignee == null) ? 0 : assignee.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
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
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (creator == null) {
            if (other.creator != null)
                return false;
        } else if (!creator.equals(other.creator))
            return false;
        if (summary == null) {
            if (other.summary != null)
                return false;
        } else if (!summary.equals(other.summary))
            return false;
        if (assignee == null) {
            if (other.assignee != null)
                return false;
        } else if (!assignee.equals(other.assignee))
            return false;
        if (created == null) {
            if (other.created != null)
                return false;
        } else if (!created.equals(other.created))
            return false;
        if (resolved == null) {
            if (other.resolved != null)
                return false;
        } else if (!resolved.equals(other.resolved))
            return false;
        if (comment == null) {
            if (other.comment != null)
                return false;
        } else if (!comment.equals(other.comment))
            return false;
        return true;
    }

}