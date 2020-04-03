package com.sunmnet.bigdata.task.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CheckInRecord implements Serializable {
    private static final long serialVersionUID = -7332499244597401319L;

    private String id;
    private String userid;
    private String groupname; // "打卡测试"
    private String checkin_type;// "上班打卡",
    private String exception_type;// "未打卡",
    private Long checkin_time;// 1584320400,
    private String location_title;
    private String location_detail;
    private String wifiname;
    private String notes;
    private String wifimac;
    private List<Object> mediaids;
    private Long lat;
    private Long lng;
    private String deviceid;
    private Date createAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getCheckin_type() {
        return checkin_type;
    }

    public void setCheckin_type(String checkin_type) {
        this.checkin_type = checkin_type;
    }

    public String getException_type() {
        return exception_type;
    }

    public void setException_type(String exception_type) {
        this.exception_type = exception_type;
    }

    public Long getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(Long checkin_time) {
        this.checkin_time = checkin_time;
    }

    public String getLocation_title() {
        return location_title;
    }

    public void setLocation_title(String location_title) {
        this.location_title = location_title;
    }

    public String getLocation_detail() {
        return location_detail;
    }

    public void setLocation_detail(String location_detail) {
        this.location_detail = location_detail;
    }

    public String getWifiname() {
        return wifiname;
    }

    public void setWifiname(String wifiname) {
        this.wifiname = wifiname;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWifimac() {
        return wifimac;
    }

    public void setWifimac(String wifimac) {
        this.wifimac = wifimac;
    }

    public List<Object> getMediaids() {
        return mediaids;
    }

    public void setMediaids(List<Object> mediaids) {
        this.mediaids = mediaids;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
