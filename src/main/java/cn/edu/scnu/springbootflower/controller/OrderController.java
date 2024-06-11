package cn.edu.scnu.springbootflower.controller;

import cn.edu.scnu.springbootflower.entity.*;
import cn.edu.scnu.springbootflower.mapper.ShoworderMapper;
import cn.edu.scnu.springbootflower.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Flow;

@Controller
public class OrderController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;
    @Autowired
    private FlowerService flowerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoplistService shoplistService;

    @RequestMapping("/order/order")
    public String order(String cartIds, HttpSession session, Model model){
        TbMember member=(TbMember) session.getAttribute("memberLogin");
        if(member==null){
            return "forward:/index/toLogin";
        }
        model.addAttribute("member", member);
        List<Customer> customers = customerService.findByEmail(member.getEmail());
        model.addAttribute("customers", customers);
        String[] arrCartIds = cartIds.split(",");
        session.setAttribute("cartIds",cartIds);
        List<Cart> carts = new ArrayList<>();
        int total = 0;
        int sum = 0;
        for(String cid:arrCartIds){
            Integer cartID = Integer.parseInt(cid);
            Cart cart = cartService.getById(cartID);
            total += cart.getNum()*cart.getYourprice();
            sum += cart.getNum();
            carts.add(cart);
        }
        model.addAttribute("sum",sum);
        model.addAttribute("total",total);
        model.addAttribute("cartIds",cartIds);
        session.setAttribute("cartIds", cartIds);
        return "order";
    }

    @RequestMapping("/order/addOrder")
    @ResponseBody
    public String addOrder(MyOrder order,HttpSession session){
        TbMember member = (TbMember) session.getAttribute("memberLogin");
        if(member==null)return "login";
        order.setEmail(member.getEmail());
        Date date = new Date();
        order.setInputtime(new Timestamp((date.getTime())));
        order.setStatus("未付款");
        orderService.save(order);

        String cartIds = (String) session.getAttribute("cartIds");
        String[] arrCartIds = cartIds.split(",");
        for(String cid:arrCartIds){
            Cart cart = cartService.getById(cid);
            Shoplist shoplist = new Shoplist();
            shoplist.setOrderId(order.getOrderId());
            shoplist.setEmail(member.getEmail());
            shoplist.setFlowerid(cart.getFlowerid());
            shoplist.setNum(cart.getNum());
            shoplist.setPjstar(5);
            shoplistService.save(shoplist);
            Flower flower = flowerService.findFlowerById(cart.getFlowerid());
            flower.setSellednum(flower.getSellednum()+cart.getNum());
            flowerService.saveOrUpdate(flower);
            cartService.removeById(cid);
        }
        return "success";
    }

    @RequestMapping("/order/showorder")
    public String showorder(HttpSession session, Model model){

        TbMember member = (TbMember) session.getAttribute("memberLogin");
        if(member==null){
            return "forward:/index/toLogin";
        }
        List<Showorder> showorders = orderService.showorder(member.getEmail());
        model.addAttribute("showorders", showorders);

        Map<Integer, List> map = new HashMap<Integer, List>();
        for(Showorder order: showorders){
            List<Showshoplist> showshoplists = orderService.findShoplistByOrderId(order.getOrderId());
            map.put(order.getOrderId(), showshoplists);

        }
        model.addAttribute("map", map);
        return "showorder";
    }

    @RequestMapping("/order/delorder")
    public String delorder(Integer id){
        orderService.removeById(id);
        shoplistService.delete(id);
        return "forward:/order/showorder";
    }

    @RequestMapping("/order/pay")
    public String pay(Integer orderId){
        orderService.updateOrder(orderId, "已付款");
        return "forward:/order/showorder";
    }

    @RequestMapping("/order/updateOrder")
    public String updateOrder(Integer orderId){
        orderService.updateOrder(orderId, "未评价");
        return "forward:/order/showorder";
    }
}
