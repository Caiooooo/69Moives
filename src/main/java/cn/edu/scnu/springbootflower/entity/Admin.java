package cn.edu.scnu.springbootflower.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private String username;
    private String password;
    private String authority;
}
