package org.wumbuk.entity;

public class GoodPhotos {
    private Integer imgid;

    private String imgMore;

    private Integer goodid;

    public Integer getImgid() {
        return imgid;
    }

    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

    public String getImgMore() {
        return imgMore;
    }

    public void setImgMore(String imgMore) {
        this.imgMore = imgMore == null ? null : imgMore.trim();
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }
}