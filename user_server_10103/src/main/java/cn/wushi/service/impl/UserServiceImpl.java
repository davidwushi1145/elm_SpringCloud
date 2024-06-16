package cn.wushi.service.impl;

import cn.wushi.common.ErrorCode;
import cn.wushi.exception.BusinessException;
import cn.wushi.mapper.UserMapper;
import cn.wushi.po.User;
import cn.wushi.po.UserVo;
import cn.wushi.service.UserService;
import cn.wushi.util.JWTUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Map<String, String> getUserByIdByPass(String userId, String password) {
        try {
            User user1 = userMapper.getUserByIdByPass(userId, password);
            if (user1 == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "数据库操作失败，用户登录失败");
            }

            //获取Token
            Map<String, String> claim = new HashMap<String, String>();
            claim.put("userId", userId);
            JWTUtil jwtUtil = new JWTUtil();
            String token = jwtUtil.getToken(claim);

            Map<String, String> jwtMap = new HashMap<String, String>();
            jwtMap.put("JWT", token);
            return jwtMap;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserVo getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    public User getUser(String userId) {
        try {
            return userMapper.getUser(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getUserById(String userId) {
        try {
            return userMapper.getUserById(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveUser(String userId, String password, String userName, Integer userSex) {
        try {
            return userMapper.saveUser(userId, password, userName, userSex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}