package com.AZDash2.valueobject;

import java.util.Set;

public class ProjectIssues {
    private Set<String> keys;
    
    public ProjectIssues() {}
    
    public ProjectIssues(Set<String> keys) {
        this.keys = keys;
    }

    public Set<String> getKey() {
        return keys;
    }

    public void setKeys(Set<String> keys) {
        this.keys = keys;
        
    }
}
