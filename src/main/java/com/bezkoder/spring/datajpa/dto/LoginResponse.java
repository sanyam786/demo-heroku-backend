package com.bezkoder.spring.datajpa.dto;

// LoginResponse DTO
public class LoginResponse {
    private String message;
    private String role;
    private Long memberId;
    private String token;

    public LoginResponse(String message, String role) {
        this.message = message;
        this.role = role;
    }

    public LoginResponse(String message, String role, Long memberId, String token) {
        this.message = message;
        this.role = role;
        this.memberId = memberId;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
