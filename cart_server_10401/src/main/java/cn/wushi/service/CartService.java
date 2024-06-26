package cn.wushi.service;

import cn.wushi.po.CartVo;

import java.util.List;

public interface CartService {
    public List<CartVo> listCart(String userId, Integer businessId);

    public int saveCart(Integer businessId, String userId, Integer foodId);

    public int updateCart(Integer businessId, Integer foodId, String userId, Integer quantity);

    public int removeCart(String userId, Integer businessId, Integer foodId);

    public CartVo getCartVoByID(Integer businessId, Integer foodId, String userId);
}
