package org.wumbuk.entity;

import java.util.List;

public class Sellers {

    private Integer sid;
    private String school;
    private Boolean ispass;
    private Integer totalnumber;
    private Float reliability;
    private String surprise;
    private String nickname;
    private String phone;
    private String bio;
    private String avatar;
    private Float score;
    private List<Activity> activities;

    @Override
    public String toString() {
        return "Sellers{" +
                "sid=" + sid +
                ", school='" + school + '\'' +
                ", ispass=" + ispass +
                ", totalnumber=" + totalnumber +
                ", reliability=" + reliability +
                ", surprise='" + surprise + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", bio='" + bio + '\'' +
                ", avatar='" + avatar + '\'' +
                ", score=" + score +
                ", activities=" + activities +
                '}';
    }

    public Sellers(){
        super();
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public Sellers(Integer sid, String school, Boolean ispass,
                   Integer totalnumber, Float reliability,
                   String surprise, String nickname, String phone,
                   String avatar, Float score, List<Activity> activities) {
        this.sid = sid;
        this.school = school;
        this.ispass = ispass;
        this.totalnumber = totalnumber;
        this.reliability = reliability;
        this.surprise = surprise;
        this.nickname = nickname;
        this.phone = phone;
        this.avatar = avatar;
        this.score = score;
        this.activities = activities;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }



    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public Boolean getIspass() {
        return ispass;
    }

    public void setIspass(Boolean ispass) {
        this.ispass = ispass;
    }

    public Integer getTotalnumber() {
        return totalnumber;
    }

    public void setTotalnumber(Integer totalnumber) {
        this.totalnumber = totalnumber;
    }

    public Float getReliability() {
        return reliability;
    }

    public void setReliability(Float reliability) {
        this.reliability = reliability;
    }

    public String getSurprise() {
        return surprise;
    }

    public void setSurprise(String surprise) {
        this.surprise = surprise == null ? null : surprise.trim();
    }
}