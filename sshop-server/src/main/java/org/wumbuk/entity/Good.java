package org.wumbuk.entity;

import java.util.List;

public class Good {
    private Integer goodid;
    private String title;
    private Integer number;
    private Float price;
    private String comment;
    private String imglink;
    private Integer sid;
    private Integer cid;
    private Integer likes;
    private Float score;
    private Integer sellnumber;
    private  String nickname;
    private List<String> otherlinks;








    @Override
    public String toString() {
        return "Good{" +
                "goodid=" + goodid +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", imglink='" + imglink + '\'' +
                ", sid=" + sid +
                ", cid=" + cid +
                ", likes=" + likes +
                ", score=" + score +
                ", sellnumber=" + sellnumber +
                ", nickname='" + nickname + '\'' +
                ", otherlinks=" + otherlinks +
                '}';
    }

    public List<String> getOtherlinks() {
        return otherlinks;
    }

    public void setOtherlinks(List<String> otherlinks) {
        this.otherlinks = otherlinks;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getSellnumber() {
        return sellnumber;
    }

    public void setSellnumber(Integer sellnumber) {
        this.sellnumber = sellnumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}