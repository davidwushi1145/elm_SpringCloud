package cn.wushi.mapper;

import cn.wushi.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where userId=#{userId} and password=#{password}")
    public User getUserByIdByPass(User user);

    @Select("select * from user where userId=#{userId}")
    public User getUserById(String userId);

    @Insert("insert into user values(#{userId},#{password},#{userName},#{userSex},null,1)")
    public int saveUser(User user);
}