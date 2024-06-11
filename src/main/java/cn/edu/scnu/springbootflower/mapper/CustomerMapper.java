package cn.edu.scnu.springbootflower.mapper;

import cn.edu.scnu.springbootflower.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}
