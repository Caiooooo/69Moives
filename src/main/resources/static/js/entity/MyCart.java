package cn.edu.scnu.entity;
import javax.persistence.Column;
public class MyCart {
    @Column(name="cart_id")
    private Integer cartId;
    private String flowerid;
    private String pictures;
    private String email;
    private Integer num;
    private String fname;
    private Integer price;
    private Integer yourprice;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getFlowerid() {
        return flowerid;
    }

    public void setFlowerid(String flowerid) {
        this.flowerid = flowerid;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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
}
