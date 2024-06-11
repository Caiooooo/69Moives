package cn.edu.scnu.springbootflower.service;

import cn.edu.scnu.springbootflower.entity.Shoplist;
import cn.edu.scnu.springbootflower.mapper.ShoplistMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoplistService extends ServiceImpl<ShoplistMapper, Shoplist>
{
    @Autowired
    private ShoplistMapper shoplistMapper;

    public void delete(Integer id){
        QueryWrapper<Shoplist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", id);
        shoplistMapper.delete(queryWrapper);

    }
}
