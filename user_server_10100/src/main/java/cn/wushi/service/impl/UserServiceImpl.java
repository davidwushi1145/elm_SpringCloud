package cn.wushi.service.impl;

import cn.wushi.common.ErrorCode;
import cn.wushi.exception.BusinessException;
import cn.wushi.mapper.UserMapper;
import cn.wushi.po.User;
import cn.wushi.po.UserVo;
import cn.wushi.repository.UserRepository;
import cn.wushi.service.UserService;
import cn.wushi.util.JWTUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String USER_CACHE_KEY_PREFIX = "user:";

    @Override
    public Map<String, String> getUserByIdByPass(String userId, String password) {
        try {
            User user1 = userMapper.getUserByIdByPass(userId, password);
            if (user1 == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "数据库操作失败，用户登录失败");
            }

            //获取Token
            Map<String, String> claim = new HashMap<>();
            claim.put("userId", userId);
            JWTUtil jwtUtil = new JWTUtil();
            String token = jwtUtil.getToken(claim);

            // 缓存用户信息到Redis
            redisTemplate.opsForValue().set(USER_CACHE_KEY_PREFIX + userId, user1, 30, TimeUnit.MINUTES);

            Map<String, String> jwtMap = new HashMap<>();
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

    @Override
    public User getUser(String userId) {
        // 先从缓存中获取用户信息
        User user = (User) redisTemplate.opsForValue().get(USER_CACHE_KEY_PREFIX + userId);
        if (user != null) {
            return user;
        }

        // 缓存中没有，从数据库获取并缓存
        try {
            user = userMapper.getUser(userId);
            if (user != null) {
                redisTemplate.opsForValue().set(USER_CACHE_KEY_PREFIX + userId, user, 30, TimeUnit.MINUTES);
            }
            return user;
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
