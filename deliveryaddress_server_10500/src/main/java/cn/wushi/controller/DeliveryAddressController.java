package cn.wushi.controller;

import cn.wushi.po.CommonResult;
import cn.wushi.po.DeliveryAddress;
import cn.wushi.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RefreshScope
@RestController
@RequestMapping("/DeliveryAddress")
public class DeliveryAddressController {
    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @GetMapping("/UserId")
    public CommonResult<List> listDeliveryAddressByUserId(DeliveryAddress deliveryAddress)
            throws Exception {
        return new CommonResult<List>(200, "success",
                deliveryAddressService.listDeliveryAddressByUserId(deliveryAddress.getUserId()));
    }

    @GetMapping("/DaId")
    public CommonResult<DeliveryAddress> getDeliveryAddressById(DeliveryAddress deliveryAddress) throws
            Exception {
        return new CommonResult<DeliveryAddress>(200, "success",
                deliveryAddressService.getDeliveryAddressById(deliveryAddress.getDaId()));
    }

    @PostMapping("/DaId")
    public CommonResult<Integer> saveDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
        return new CommonResult<Integer>(200, "success",
                deliveryAddressService.saveDeliveryAddress(deliveryAddress));
    }

    @PutMapping("/DaId")
    public CommonResult<Integer> updateDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
        return new CommonResult<Integer>(200, "success",
                deliveryAddressService.updateDeliveryAddress(deliveryAddress));
    }

    @DeleteMapping("/DaId")
    public CommonResult<Integer> removeDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
        return new CommonResult<Integer>(200, "success",
                deliveryAddressService.removeDeliveryAddress(deliveryAddress.getDaId()));
    }
}