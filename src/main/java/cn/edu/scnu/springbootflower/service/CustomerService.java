package cn.edu.scnu.springbootflower.service;

import cn.edu.scnu.springbootflower.entity.Customer;
import cn.edu.scnu.springbootflower.mapper.CustomerMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.QuitResponse;
import java.util.List;

@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> {
    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> findByEmail(String email){
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        return customerMapper.selectList(queryWrapper);
    }

    public void setDefault(int custId, int originalId){
        Customer customer= customerMapper.selectById(custId);
        Customer _customer = customerMapper.selectById(originalId);
        _customer.setCdefault("0");
        customer.setCdefault("1");
        customerMapper.updateById(_customer);
        customerMapper.updateById(customer);
    }

    public void editCustomer(Customer customer){
        customerMapper.updateById(customer);
    }
}
