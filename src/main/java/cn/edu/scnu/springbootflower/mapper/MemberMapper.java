package cn.edu.scnu.springbootflower.mapper;

import cn.edu.scnu.springbootflower.entity.TbMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<TbMember> {
}
