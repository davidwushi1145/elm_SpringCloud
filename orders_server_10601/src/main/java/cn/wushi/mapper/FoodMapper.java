package cn.wushi.mapper;

import cn.wushi.po.Food;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
    @Select("select * from food where businessId=#{businessId} order by foodId")
    public List<Food> listFoodByBusinessId(@Param("businessId") Integer businessId) throws SQLException;

    @Select("select * from food where foodId=#{foodId}")
    public Food getFoodById(@Param("foodId") Integer foodId) throws SQLException;

    @Select("SELECT * FROM food WHERE foodId = #{foodId} AND businessId = #{businessId}")
    Food getFoodByIdAndBusinessId(@Param("foodId") Integer foodId, @Param("businessId") Integer businessId);

}