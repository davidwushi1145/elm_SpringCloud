package cn.wushi.po;

import lombok.Data;

@Data
public class OrderDetailetVo {
    private Integer odId;
    private Integer orderId;
    private Integer foodId;
    private Integer businessId;
    private Integer quantity;

    //多对一：所属食品
    private FoodVo foodVo;
}
