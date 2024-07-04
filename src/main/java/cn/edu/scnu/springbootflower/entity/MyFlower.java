package cn.edu.scnu.springbootflower.entity;

import org.springframework.web.multipart.MultipartFile;

public class MyFlower {
    private String fname;
    private String myclass;
    private String fclass;
    private String fclass1;
    private String cailiao;
    private String baozhuang;
    private String huayu;
    private String shuoming;
    private Integer price;
    private Integer yourprice;
    private MultipartFile pictures;
    private MultipartFile picturem;
    private MultipartFile pictureb;
    private MultipartFile pictured;
    private MultipartFile cailiaopicture;
    private MultipartFile bzpicture;
    private String tejia;

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    private String vip;

    //自动生成getter和setter

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMyclass() {
        return myclass;
    }

    public void setMyclass(String myclass) {
        this.myclass = myclass;
    }

    public String getFclass() {
        return fclass;
    }

    public void setFclass(String fclass) {
        this.fclass = fclass;
    }

    public String getFclass1() {
        return fclass1;
    }

    public void setFclass1(String fclass1) {
        this.fclass1 = fclass1;
    }

    public String getCailiao() {
        return cailiao;
    }

    public void setCailiao(String cailiao) {
        this.cailiao = cailiao;
    }

    public String getBaozhuang() {
        return baozhuang;
    }

    public void setBaozhuang(String baozhuang) {
        this.baozhuang = baozhuang;
    }

    public String getHuayu() {
        return huayu;
    }

    public void setHuayu(String huayu) {
        this.huayu = huayu;
    }

    public String getShuoming() {
        return shuoming;
    }

    public void setShuoming(String shuoming) {
        this.shuoming = shuoming;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getYourprice() {
        return yourprice;
    }

    public void setYourprice(Integer yourprice) {
        this.yourprice = yourprice;
    }

    public MultipartFile getPictures() {
        return pictures;
    }

    public void setPictures(MultipartFile pictures) {
        this.pictures = pictures;
    }

    public MultipartFile getPicturem() {
        return picturem;
    }

    public void setPicturem(MultipartFile picturem) {
        this.picturem = picturem;
    }

    public MultipartFile getPictureb() {
        return pictureb;
    }

    public void setPictureb(MultipartFile pictureb) {
        this.pictureb = pictureb;
    }

    public MultipartFile getPictured() {
        return pictured;
    }

    public void setPictured(MultipartFile pictured) {
        this.pictured = pictured;
    }

    public MultipartFile getCailiaopicture() {
        return cailiaopicture;
    }

    public void setCailiaopicture(MultipartFile cailiaopicture) {
        this.cailiaopicture = cailiaopicture;
    }

    public MultipartFile getBzpicture() {
        return bzpicture;
    }

    public void setBzpicture(MultipartFile bzpicture) {
        this.bzpicture = bzpicture;
    }

    public String getTejia() {
        return tejia;
    }

    public void setTejia(String tejia) {
        this.tejia = tejia;
    }
}
