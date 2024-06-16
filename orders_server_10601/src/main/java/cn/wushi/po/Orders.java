package cn.wushi.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Orders {
    @TableId
    private Integer orderId;
    private String userId;
    private Integer businessId;
    private String orderDate;
    private Double orderTotal;
    private Integer daId; //送货地址编号
    private Integer orderState; //订单状态（0：未支付； 1：已支付）

    //多对一：所属商家
    private Business business;
    //一对多：订单明细
    private List<OrderDetailet> list;

}
