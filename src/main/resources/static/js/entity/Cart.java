package cn.edu.scnu.entity;
import javax.persistence.*;

@Entity(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Integer cartId;
    private String email;
    private String flowerid;
    private Integer num;

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
