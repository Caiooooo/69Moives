package cn.edu.scnu.springbootflower.controller;

import cn.edu.scnu.springbootflower.common.MD5Util;
import cn.edu.scnu.springbootflower.entity.Admin;
import cn.edu.scnu.springbootflower.entity.Showorder;
import cn.edu.scnu.springbootflower.entity.Showshoplist;
import cn.edu.scnu.springbootflower.service.AdminService;
import cn.edu.scnu.springbootflower.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/admin/doAdminLogin")
    @ResponseBody
    public String doAdminLogin(String username, String password, HttpSession session){
        Admin admin = adminService.getById(username);
        if(admin != null && admin.getPassword().equals(MD5Util.md5(password))){
            session.setAttribute("admin", admin);
            return "登录成功";
        }else{
            return "登陆失败";
        }
    }

    @RequestMapping("/admin/toAdminLogin")
    public String toAdminLogin(){
        return "adminlogin";
    }

    //后台主页
    @RequestMapping("/admin/adminindex")
    public String adminindex(HttpSession session){
        if(session.getAttribute("admin")==null){
            return "redirect:/admin/toAdminLogin";
        }
        return "adminindex";
    }

    //exit
    @RequestMapping("/admin/adminlogOut")
    public String adminlogOut(HttpSession session){
        session.setAttribute("admin", null);
        return "redirect:/index";
    }

    @RequestMapping("/admin/order/orderlist")
    public String toOrderList(String status, Model model, HttpSession session){
        if(session.getAttribute("admin")==null){
            return "redirect:/admin/toAdminLogin";
        }
        String _status = "已付款";
        switch (status){
            case "1":_status="未付款";break;
            case "2":_status="已付款";break;
            case "3":_status="已发货";break;
            case "4":_status="未评价";break;
            case "5":_status="已评价";break;
            case "0":_status="";break; //显示所有订单

        }
        List<Showorder> showorders = orderService.showorderByStatus(_status);
        model.addAttribute("showorders", showorders);
        Map<Integer, List> map = new HashMap<Integer, List>();
        for(Showorder order:showorders){
            List<Showshoplist> showshoplists=
                    orderService.findShoplistByOrderId(order.getOrderId());
            map.put(order.getOrderId(), showshoplists);
        }
        model.addAttribute("map", map);
        return "adminshoworder";
    }

    @RequestMapping("/admin/send")
    public String updateOrder(Integer orderId, HttpSession session){
        if(session.getAttribute("admin")==null)
            return "redirect:/admin/toAdminLogin";
        orderService.updateOrder(orderId, "已发货");
        return "forward:/admin/order/orderlist?status=2";
    }
}
