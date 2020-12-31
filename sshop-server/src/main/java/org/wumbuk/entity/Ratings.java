package org.wumbuk.entity;

import java.util.Date;

public class Ratings {
    private Integer ratingid;

    private Integer orderid;

    private Date ratetime;

    private Float ratescore;

    private Integer ratetype;

    private String text;
    private String nickname;
    private String avatar;
    private String title;
    private Long unixtime;


    @Override
    public String toString() {
        return "Ratings{" +
                "ratingid=" + ratingid +
                ", orderid=" + orderid +
                ", ratetime=" + ratetime +
                ", ratescore=" + ratescore +
                ", ratetype=" + ratetype +
                ", text='" + text + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", title='" + title + '\'' +
                ", unixtime=" + unixtime +
                '}';
    }

    public Long getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(Long unixtime) {
        this.unixtime = unixtime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRatingid() {
        return ratingid;
    }

    public void setRatingid(Integer ratingid) {
        this.ratingid = ratingid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Date getRatetime() {
        return ratetime;
    }

    public void setRatetime(Date ratetime) {
        this.ratetime = ratetime;
    }

    public Float getRatescore() {
        return ratescore;
    }

    public void setRatescore(Float ratescore) {
        this.ratescore = ratescore;
    }

    public Integer getRatetype() {
        return ratetype;
    }

    public void setRatetype(Integer ratetype) {
        this.ratetype = ratetype;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}