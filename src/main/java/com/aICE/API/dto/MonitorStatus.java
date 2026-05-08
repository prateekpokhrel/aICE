package com.aICE.API.dto;

public class MonitorStatus {

    private String backend;

    private String database;

    private String redis;

    private String docker;

    public MonitorStatus() {
    }

    public MonitorStatus(
            String backend,
            String database,
            String redis,
            String docker
    ) {
        this.backend = backend;
        this.database = database;
        this.redis = redis;
        this.docker = docker;
    }

    public String getBackend() {
        return backend;
    }

    public void setBackend(String backend) {
        this.backend = backend;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getRedis() {
        return redis;
    }

    public void setRedis(String redis) {
        this.redis = redis;
    }

    public String getDocker() {
        return docker;
    }

    public void setDocker(String docker) {
        this.docker = docker;
    }
}