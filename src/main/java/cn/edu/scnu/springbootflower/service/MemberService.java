package cn.edu.scnu.springbootflower.service;

import cn.edu.scnu.springbootflower.common.MD5Util;
import cn.edu.scnu.springbootflower.entity.TbMember;
import cn.edu.scnu.springbootflower.mapper.MemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends ServiceImpl<MemberMapper, TbMember> {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public TbMember login(String email, String password){
        TbMember member = memberMapper.selectById(email);
        if(member!=null && member.getPassword().equals(MD5Util.md5(password)))
            return member;
        else
            return null;
    }
}
