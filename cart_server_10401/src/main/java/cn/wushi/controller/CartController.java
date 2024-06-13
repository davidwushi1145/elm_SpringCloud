package cn.wushi.controller;

import cn.wushi.po.Cart;
import cn.wushi.po.CommonResult;
import cn.wushi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/CartList")
    public CommonResult<List> listCart(Cart cart) throws Exception {
        return new CommonResult<List>(200, "success", cartService.listCart(cart));
    }


    @PostMapping("/Order")
    public CommonResult<Integer> saveCart(Cart cart) throws Exception {
        return new CommonResult<Integer>(200, "success", cartService.saveCart(cart));
    }

    @PutMapping("/Order")
    public CommonResult<Integer> updateCart(Cart cart) throws Exception {
        return new CommonResult<Integer>(200, "success", cartService.updateCart(cart));
    }

    @DeleteMapping("/Order")
    public CommonResult<Integer> removeCart(Cart cart) throws Exception {
        return new CommonResult<Integer>(200, "success", cartService.removeCart(cart));
    }

}