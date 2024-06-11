package cn.edu.scnu.springbootflower.service;


import cn.edu.scnu.springbootflower.entity.Admin;
import cn.edu.scnu.springbootflower.mapper.AdminMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminService extends ServiceImpl<AdminMapper, Admin> {
    @Autowired
    private AdminMapper adminMapper;
}
