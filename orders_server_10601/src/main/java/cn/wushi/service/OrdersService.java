package cn.wushi.service;

import cn.wushi.po.OrderDetailet;
import cn.wushi.po.OrdersVo;

import java.util.List;

public interface OrdersService {
    public int createOrders(String userId, Integer businessId, Integer daId, Double orderTotal);

    public OrdersVo getOrdersById(Integer orderId);

    List<OrdersVo> listOrdersByUserId(String userId, int page, int size);

    public int updateOrder(Integer orderId, Integer orderState);

    public int updateOrders(Integer orderId, Double orderTotal);

    public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId);
}
