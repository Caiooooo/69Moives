package cn.edu.scnu.springbootflower.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "tb_member")
public class TbMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private String email;
    private String password;
    private String mname;
    private String mobile;
    private String address;
    private int jifen;
    private double ye;
    private int isvip;
}
