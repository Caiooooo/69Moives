package cn.edu.scnu.springbootflower.controller;

import cn.edu.scnu.springbootflower.entity.Customer;
import cn.edu.scnu.springbootflower.entity.TbMember;
import cn.edu.scnu.springbootflower.service.CustomerService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customer/addCustomer")
    @ResponseBody
    public Customer addCustomer(String addName, String addPhone, String address, HttpSession session){
        TbMember member=(TbMember) session.getAttribute("memberLogin");
        Customer customer = new Customer();
        customer.setSname(addName);
        customer.setMobile(addPhone);
        customer.setAddress(address);
        customer.setEmail(member.getEmail());
        List<Customer> customers = customerService.findByEmail(member.getEmail());
        if(customers!=null && !customers.isEmpty()){
            customer.setCdefault("0");
        }else{
            customer.setCdefault("1");
        }
        customerService.save(customer);
        return customer;
    }

    @RequestMapping("/customer/deleteCustomer")
    @ResponseBody
    public String deleteCustomer(int custId){
        customerService.removeById(custId);
        return "success";
    }

    @RequestMapping("/customer/setDefault")
    @ResponseBody
    public String setDefault(int custId, int originalId){
        customerService.setDefault(custId, originalId);
        return "success";
    }

    @RequestMapping("/customer/editCustomer")
    @ResponseBody
    public String editCustomer(Customer customer){
        customerService.editCustomer(customer);
        return "success";
    }
}
