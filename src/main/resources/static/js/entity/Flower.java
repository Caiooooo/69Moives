package cn.edu.scnu.entity;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity(name="flower")
public class Flower {
    @Id
    private String flowerid;
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
    private String pictures;
    private String picturem;
    private String pictureb;
    private String pictured;
    private String cailiaopicture;
    private String bzpicture;
    private String tejia;
    private Integer sellednum;

    public String getFlowerid() {
        return flowerid;
    }

    public void setFlowerid(String flowerid) {
        this.flowerid = flowerid;
    }

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

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getPicturem() {
        return picturem;
    }

    public void setPicturem(String picturem) {
        this.picturem = picturem;
    }

    public String getPictureb() {
        return pictureb;
    }

    public void setPictureb(String pictureb) {
        this.pictureb = pictureb;
    }

    public String getPictured() {
        return pictured;
    }

    public void setPictured(String pictured) {
        this.pictured = pictured;
    }

    public String getCailiaopicture() {
        return cailiaopicture;
    }

    public void setCailiaopicture(String cailiaopicture) {
        this.cailiaopicture = cailiaopicture;
    }

    public String getBzpicture() {
        return bzpicture;
    }

    public void setBzpicture(String bzpicture) {
        this.bzpicture = bzpicture;
    }

    public String getTejia() {
        return tejia;
    }

    public void setTejia(String tejia) {
        this.tejia = tejia;
    }

    public Integer getSelledNum() {
        return sellednum;
    }

    public void setSelledNum(Integer selledNum) {
        this.sellednum = selledNum;
    }
}
