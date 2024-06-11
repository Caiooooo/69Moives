package cn.edu.scnu.springbootflower.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_customer")
public class Customer {
    @TableId(type= IdType.AUTO)
    private Integer custId;
    private String email;
    private String sname;
    private String sex;
    private String mobile;
    private String address;
    private String zip;
    private String cdefault;
}
