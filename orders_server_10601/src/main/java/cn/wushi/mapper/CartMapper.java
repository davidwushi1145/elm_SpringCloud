package cn.wushi.mapper;

import cn.wushi.po.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    public List<Cart> listCart(String userId, Integer businessId);

    @Insert("insert into cart(foodId, businessId, userId, quantity, isDelete) values(#{foodId},#{businessId},#{userId},1,0)")
    public int saveCart(Integer businessId, String userId, Integer foodId) throws SQLException;

    @Select("select * from cart where foodId=#{foodId} and businessId=#{businessId} and userId=#{userId}")
    public Cart getCartById(Integer foodId, Integer businessId, String userId) throws SQLException;
    @Update("update cart set quantity=#{quantity} where foodId=#{foodId} and businessId=#{businessId} and userId=#{userId}")
    public int updateCart(Integer businessId, Integer foodId, String userId, Integer quantity) throws SQLException;

    public int removeCart(String userId, Integer businessId, Integer foodId) throws SQLException;
}