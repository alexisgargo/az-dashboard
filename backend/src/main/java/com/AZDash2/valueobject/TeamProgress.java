package com.AZDash2.valueobject;

public class TeamProgress {
    private String progress;
    private String team;
    private String version;

    public TeamProgress() {}

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public TeamProgress(String Progress) {
        this.progress = Progress;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}

