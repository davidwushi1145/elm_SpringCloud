package cn.wushi.controller;

import cn.wushi.common.BaseResponse;
import cn.wushi.common.ErrorCode;
import cn.wushi.common.ResultUtils;
import cn.wushi.common.UserSupport;
import cn.wushi.exception.BusinessException;
import cn.wushi.po.CartVo;
import cn.wushi.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserSupport userSupport;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @GetMapping("/lists")
    public BaseResponse<List<CartVo>> listCart(
            @RequestParam("businessId") Integer businessId) {

        String userId = userSupport.getCurrentUserId();
        List<CartVo> cartVoList = cartService.listCart(userId, businessId);
        if (cartVoList != null) {
            return ResultUtils.success(cartVoList);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，获取购物车列表失败");
        }
    }

    @PostMapping("/newCarts")
    public BaseResponse<CartVo> saveCart(@RequestParam("businessId") Integer businessId,
                                         @RequestParam("foodId") Integer foodId) {
        if (businessId == null || foodId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不可为空");
        }
        String userId = userSupport.getCurrentUserId();
        // 记录日志
        logger.debug("Saving cart for userId: {}", userId);
        Integer result = cartService.saveCart(businessId, userId, foodId);
        if (result.equals(1)) {
            return ResultUtils.success(cartService.getCartVoByID(businessId, foodId, userId));
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，新增购物车失败");
        }
    }

    @PostMapping("/updated-carts")
    public BaseResponse<Integer> updateCart(@RequestParam("businessId") Integer businessId,
                                            @RequestParam("foodId") Integer foodId,
                                            @RequestParam("quantity") Integer quantity) {
        String userId = userSupport.getCurrentUserId();
        if (businessId == null || foodId == null || userId == null || quantity == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不可为空");
        }
        if (quantity <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "商品数量不可为空");
        }
        Integer result = cartService.updateCart(businessId, foodId, userId, quantity);
        if (result.equals(1)) {
            return ResultUtils.success(result);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，更新购物车失败");
        }
    }

    @DeleteMapping("/removed-carts")
    public BaseResponse<Integer> removeCart(
            @RequestParam("businessId") Integer businessId,
            @RequestParam("foodId") Integer foodId) {
        String userId = userSupport.getCurrentUserId();
        if (userId == null || businessId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不可为空");
        }
        Integer result = cartService.removeCart(userId, businessId, foodId);
        if (result.equals(1)) {
            return ResultUtils.success(result);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，移除购物车失败");
        }
    }
}
