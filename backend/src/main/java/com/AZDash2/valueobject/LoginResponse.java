package com.AZDash2.valueobject;

public class LoginResponse {
    private Boolean success;
    private String message;
    private String username;

    public LoginResponse(Boolean success, String message, String username) {
        this.success = success;
        this.message = message;
        this.username = username;
    }
    
    public LoginResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}
