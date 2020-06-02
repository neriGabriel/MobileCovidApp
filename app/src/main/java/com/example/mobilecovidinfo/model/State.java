package com.example.mobilecovidinfo.model;

import java.io.Serializable;

public class State implements Serializable {
    private int uid;
    private String uf;
    private String state;
    private Integer cases;
    private Integer deaths;
    private Integer suspects;
    private Integer refuses;
    private Boolean broadcast;
    private String comments;
    private String datetime;

    public State() {
    }

    public State(int uid, String uf, String state, Integer cases, Integer deaths, Integer suspects, Integer refuses, Boolean broadcast, String comments, String datetime) {
        this.uid = uid;
        this.uf = uf;
        this.state = state;
        this.cases = cases;
        this.deaths = deaths;
        this.suspects = suspects;
        this.refuses = refuses;
        this.broadcast = broadcast;
        this.comments = comments;
        this.datetime = datetime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getSuspects() {
        return suspects;
    }

    public void setSuspects(Integer suspects) {
        this.suspects = suspects;
    }

    public Integer getRefuses() {
        return refuses;
    }

    public void setRefuses(Integer refuses) {
        this.refuses = refuses;
    }

    public Boolean getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(Boolean broadcast) {
        this.broadcast = broadcast;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
