package cn.edu.scnu.springbootflower.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Alipayorder {
    @TableId(type = IdType.AUTO)
    private Integer order_id;
    private Long traceNo;
    private Integer status;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Long getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(Long traceNo) {
        this.traceNo = traceNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
