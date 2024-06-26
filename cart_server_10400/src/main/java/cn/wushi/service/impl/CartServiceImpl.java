package cn.wushi.service.impl;

import cn.wushi.mapper.CartMapper;
import cn.wushi.po.Cart;
import cn.wushi.po.CartVo;
import cn.wushi.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<CartVo> listCart(String userId, Integer businessId) {
        List<Cart> cartList = cartMapper.listCart(userId, businessId);
        return getCartVo(cartList);
    }

    @Override
    public int saveCart(Integer businessId, String userId, Integer foodId) {
        try {
            return cartMapper.saveCart(businessId, userId, foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateCart(Integer businessId, Integer foodId, String userId, Integer quantity) {
        try {
            return cartMapper.updateCart(businessId, foodId, userId, quantity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int removeCart(String userId, Integer businessId, Integer foodId) {
        try {
            // 最底层的 不用变
            return cartMapper.removeCart(userId, businessId, foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CartVo getCartVo(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartVo cartVo = new CartVo();
        BeanUtils.copyProperties(cart, cartVo);
        return cartVo;
    }


    public List<CartVo> getCartVo(List<Cart> cartList) {
        if (CollectionUtils.isEmpty(cartList)) {
            return new ArrayList<>();
        }
        return cartList.stream().map(this::getCartVo).collect(Collectors.toList());
    }

    public CartVo getCartVoByID(Integer foodId, Integer businessId, String userId) {
        try {
            Cart cart = cartMapper.getCartById(businessId, foodId, userId);
            return getCartVo(cart);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
