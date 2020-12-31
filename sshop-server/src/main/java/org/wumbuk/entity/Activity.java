package org.wumbuk.entity;

public class Activity {
    private Integer aid;

    private String title;

    private String descripition;

    private Integer type;

    //代表当前活动的状态
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Activity(){
        super();
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescripition() {
        return descripition;
    }

    public void setDescripition(String descripition) {
        this.descripition = descripition == null ? null : descripition.trim();
    }


    @Override
    public String toString() {
        return "Activity{" +
                "aid=" + aid +
                ", title='" + title + '\'' +
                ", descripition='" + descripition + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }


}