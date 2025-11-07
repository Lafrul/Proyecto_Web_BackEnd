package com.example.delahuerta.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class RequestLog {
    @Id @GeneratedValue
    private Long id;
    private String method;
    private String path;
    private String ip;
    private LocalDateTime timestamp;

    // Getters
    public Long getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getIp() {
        return ip;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}