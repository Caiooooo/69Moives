package cn.edu.scnu.springbootflower.mapper;

import cn.edu.scnu.springbootflower.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}
