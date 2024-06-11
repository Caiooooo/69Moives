package cn.edu.scnu.springbootflower.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;
@Data
@TableName("myorder")
public class MyOrder {
    @TableId(type= IdType.AUTO)
    private Integer orderId;
    private String email;
    private Integer custId;
    private Integer shifu;
    private Timestamp inputtime;
    private String peisongday;
    private String peisongtime;
    private String status;
}
