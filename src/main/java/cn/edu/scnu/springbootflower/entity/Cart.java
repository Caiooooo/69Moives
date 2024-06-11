package cn.edu.scnu.springbootflower.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Cart {
    private static final long serialVersionUID = 1;
    @TableId(type = IdType.AUTO)
    private Integer cartId;
    private String email;
    private String flowerid;
    private Integer num;

    private String fname;
    private String pictures;
    private Integer price;
    private Integer yourprice;


    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlowerid() {
        return flowerid;
    }

    public void setFlowerid(String flowerid) {
        this.flowerid = flowerid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
