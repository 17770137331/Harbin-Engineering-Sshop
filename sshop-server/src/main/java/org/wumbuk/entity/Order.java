package org.wumbuk.entity;

import java.util.Date;

public class Order {
    private String sellertitle;
    private String userbuytitle;
    private String imglink;
    private String goodtitle;
    private Integer orderid;
    private Integer userbuyid;
    private Integer usersellid;
    private Integer goodid;
    private Integer number;
    private Float singleprice;
    private Float totalprice;
    private Integer status;
    private String comments;
    private Date ordertime;
    public String getSellertitle() {
        return sellertitle;
    }

    public void setSellertitle(String sellertitle) {
        this.sellertitle = sellertitle;
    }

    public String getUserbuytitle() {
        return userbuytitle;
    }

    public void setUserbuytitle(String userbuytitle) {
        this.userbuytitle = userbuytitle;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sellertitle='" + sellertitle + '\'' +
                ", userbuytitle='" + userbuytitle + '\'' +
                ", imglink='" + imglink + '\'' +
                ", goodtitle='" + goodtitle + '\'' +
                ", orderid=" + orderid +
                ", userbuyid=" + userbuyid +
                ", usersellid=" + usersellid +
                ", goodid=" + goodid +
                ", number=" + number +
                ", singleprice=" + singleprice +
                ", totalprice=" + totalprice +
                ", status=" + status +
                ", comments='" + comments + '\'' +
                ", ordertime=" + ordertime +
                '}';
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getGoodtitle() {
        return goodtitle;
    }

    public void setGoodtitle(String goodtitle) {
        this.goodtitle = goodtitle;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserbuyid() {
        return userbuyid;
    }

    public void setUserbuyid(Integer userbuyid) {
        this.userbuyid = userbuyid;
    }

    public Integer getUsersellid() {
        return usersellid;
    }

    public void setUsersellid(Integer usersellid) {
        this.usersellid = usersellid;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getSingleprice() {
        return singleprice;
    }

    public void setSingleprice(Float singleprice) {
        this.singleprice = singleprice;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }
}