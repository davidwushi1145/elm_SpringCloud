package cn.wushi.service;

import cn.wushi.po.Orders;

import java.util.List;


public interface OrdersService {
    public int createOrders(Orders orders);

    public Orders getOrdersById(Integer orderId);

    public List<Orders> listOrdersByUserId(String userId);
}
