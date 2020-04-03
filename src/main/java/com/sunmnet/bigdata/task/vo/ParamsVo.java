package com.sunmnet.bigdata.task.vo;

import java.io.Serializable;

public class ParamsVo implements Serializable {
    private static final long serialVersionUID = 3364884738285203320L;

    private String corpID;
    private String secret;
    private String[] userId;
    private String startTime;
    private String endTime;

    public String getCorpID() {
        return corpID;
    }

    public void setCorpID(String corpID) {
        this.corpID = corpID;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String[] getUserId() {
        return userId;
    }

    public void setUserId(String[] userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
