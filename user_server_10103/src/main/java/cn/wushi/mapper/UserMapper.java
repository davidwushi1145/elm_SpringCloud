package cn.wushi.mapper;

import cn.wushi.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE userId=#{userId} AND password=#{password}")
    public User getUserByIdByPass(@Param("userId") String userId, @Param("password") String password) throws SQLException;


    @Select("select count(*) from user where userId=#{userId}")
    public int getUserById(String userId) throws SQLException;

    @Select("SELECT userId, userImg, userName, userSex FROM user WHERE userId=#{userId}")
    public User getUser(String userId) throws SQLException;

    @Insert("insert into user values(#{userId},#{password},#{userName},#{userSex},null,1,0)")
    public int saveUser(String userId, String password, String userName,Integer userSex) throws SQLException;

}