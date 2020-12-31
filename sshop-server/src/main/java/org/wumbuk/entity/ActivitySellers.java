package org.wumbuk.entity;

public class ActivitySellers {
    private Integer aid;

    private Integer userid;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "ActivitySellers{" +
                "aid=" + aid +
                ", userid=" + userid +
                '}';
    }
}