package cn.edu.scnu.springbootflower.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.sql.Timestamp;
@Data
public class Shoplist {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer orderId;
    private String flowerid;
    private Integer num;
    private String email;
    private String pjcontent;
    private int pjstar;
    private Timestamp pjtime;
    private String pjip;
    private String pjreply;
}
