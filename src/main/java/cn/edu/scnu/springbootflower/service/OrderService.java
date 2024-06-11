package cn.edu.scnu.springbootflower.service;

import cn.edu.scnu.springbootflower.entity.MyOrder;
import cn.edu.scnu.springbootflower.entity.Shoplist;
import cn.edu.scnu.springbootflower.entity.Showorder;
import cn.edu.scnu.springbootflower.entity.Showshoplist;
import cn.edu.scnu.springbootflower.mapper.OrderMapper;
import cn.edu.scnu.springbootflower.mapper.ShoworderMapper;
import cn.edu.scnu.springbootflower.mapper.ShowshoplistMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.List;

@Service
public class OrderService extends ServiceImpl<OrderMapper, MyOrder>
{
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ShoworderMapper showorderMapper;
    @Autowired
    private ShowshoplistMapper showshoplistMapper;

    public OrderService(OrderMapper orderMapper, ShoworderMapper showorderMapper, ShowshoplistMapper showshoplistMapper){
        this.orderMapper = orderMapper;
        this.showorderMapper = showorderMapper;
        this.showshoplistMapper = showshoplistMapper;
    }

    public List<Showorder> showorder(String email){
        QueryWrapper<Showorder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.orderByDesc("order_id");
        return showorderMapper.selectList(queryWrapper);
    }
    public List<Showshoplist> findShoplistByOrderId(Integer orderId){
        QueryWrapper<Showshoplist> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return showshoplistMapper.selectList(queryWrapper);
    }

    public void updateOrder(Integer orderId, String status){
        MyOrder order = orderMapper.selectById(orderId);
        order.setStatus(status);
        orderMapper.updateById(order);
    }

    public List<Showorder> showorderByStatus(String status){
        QueryWrapper<Showorder> queryWrapper = new QueryWrapper<>();
        if(status!=""){
            queryWrapper.eq("status", status);
        }
        queryWrapper.orderByDesc("order_id");
        return showorderMapper.selectList(queryWrapper);
    }
}
