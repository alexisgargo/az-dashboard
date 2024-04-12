package com.AZDash2.valueobject;

public class TeamProgress {
    private String Progress;
    


    public TeamProgress() {}

    public TeamProgress(String Progress) {
        this.Progress = Progress;
    }

    public String getProgress() {
        return Progress;
    }

    public void setProgress(String progress) {
        Progress = progress;
    }
}