package cn.wushi.mapper;

import cn.wushi.po.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    List<Cart> listCart(@Param("userId") String userId, @Param("businessId") Integer businessId);

    @Insert("INSERT INTO cart(foodId, businessId, userId, quantity, isDelete) VALUES(#{foodId}, #{businessId}, #{userId}, 1, 0)")
    int saveCart(@Param("businessId") Integer businessId, @Param("userId") String userId, @Param("foodId") Integer foodId) throws SQLException;

    @Select("SELECT * FROM cart WHERE foodId=#{foodId} AND businessId=#{businessId} AND userId=#{userId}")
    Cart getCartById(@Param("foodId") Integer foodId, @Param("businessId") Integer businessId, @Param("userId") String userId) throws SQLException;

    @Update("UPDATE cart SET quantity=#{quantity} WHERE foodId=#{foodId} AND businessId=#{businessId} AND userId=#{userId}")
    int updateCart(@Param("businessId") Integer businessId, @Param("foodId") Integer foodId, @Param("userId") String userId, @Param("quantity") Integer quantity) throws SQLException;

    int removeCart(@Param("userId") String userId, @Param("businessId") Integer businessId, @Param("foodId") Integer foodId) throws SQLException;
}
