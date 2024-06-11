package cn.edu.scnu.springbootflower.service;

import cn.edu.scnu.springbootflower.entity.Cart;
import cn.edu.scnu.springbootflower.entity.Flower;
import cn.edu.scnu.springbootflower.mapper.CartMapper;
import cn.edu.scnu.springbootflower.mapper.FlowerMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService extends ServiceImpl<CartMapper, Cart> {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private FlowerMapper flowermapper;

    public void addCart(Cart cart){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", cart.getEmail());
        queryWrapper.eq("flowerid", cart.getFlowerid());
        Cart _cart = cartMapper.selectOne(queryWrapper);
        if(_cart==null){
            Flower flower = flowermapper.selectById(cart.getFlowerid());
            cart.setFname(flower.getFname());
            cart.setPictures(flower.getPictures());
            cart.setPrice(flower.getPrice());
            cart.setYourprice(flower.getYourprice());
            cartMapper.insert(cart);
        }else{
            _cart.setNum(_cart.getNum()+cart.getNum());
            cartMapper.updateById(_cart);
        }
    }
    public List<Cart> showCart(String email){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return cartMapper.selectList(queryWrapper);
    }

    public void updateCart(Integer cartId, Integer num){
        Cart cart = cartMapper.selectById(cartId);
        cart.setNum(num);
        cartMapper.updateById(cart);
    }
}
