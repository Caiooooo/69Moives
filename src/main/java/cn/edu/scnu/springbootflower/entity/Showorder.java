package cn.edu.scnu.springbootflower.entity;

import lombok.Data;
import java.sql.Timestamp;
@Data
public class Showorder{
    private Integer orderId;
    private String email;
    private Timestamp inputtime;
    private String sname;
    private String address;
    private int shifu;
    private String peisongday;
    private String status;
}
