package cn.wushi.controller;

import cn.wushi.po.CommonResult;
import cn.wushi.po.Orders;
import cn.wushi.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RefreshScope
@RestController
@RequestMapping("/Orders")
public class OrdersController {
    @Autowired

    private OrdersService ordersService;

    @PostMapping("/OrdersId")
    public CommonResult<Integer> createOrders(Orders orders) throws Exception {
        return new CommonResult<>(200, "success", ordersService.createOrders(orders));
    }

    @GetMapping("/OrdersId")
    public CommonResult<Orders> getOrderById(Orders orders) throws Exception {
        return new CommonResult<>(200, "success", ordersService.getOrdersById(orders.getOrderId()));
    }

    @GetMapping("/UserId")
    public CommonResult<List> listOrdersByUserId(Orders orders) throws Exception {
        return new CommonResult<>(200, "success", ordersService.listOrdersByUserId(orders.getUserId()));
    }
}