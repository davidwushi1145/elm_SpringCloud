package cn.wushi.controller;

import cn.wushi.common.BaseResponse;
import cn.wushi.common.ErrorCode;
import cn.wushi.common.ResultUtils;
import cn.wushi.common.UserSupport;
import cn.wushi.exception.BusinessException;
import cn.wushi.po.User;
import cn.wushi.service.UserService;
import cn.wushi.util.RSAUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "用户登录、注册、信息查询")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserSupport userSupport;


    /**
     * 用户登录
     * 虽然没有涉及到业务数据的变化，但是登录还是要用post方法以掩盖敏感信息
     *
     * @param userId
     * @param password
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<Map<String, String>> getUserByIdByPass(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        if (StringUtils.isAnyBlank(userId, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不可为空");
        }
        Map<String, String> result = userService.getUserByIdByPass(userId, password);
        if (!result.isEmpty()) {
            return ResultUtils.success(result);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，用户登录失败");
        }
    }

    /**
     * 避免重复用户
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public BaseResponse<Integer> getUserById(@PathVariable(value = "userId") String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不可为空");
        }
        Integer result = userService.getUserById(userId);
        if (result.equals(1)) {
            return ResultUtils.success(result);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，查询用户失败");
        }
    }

    @GetMapping("/info")
    public BaseResponse<User> getUserInfo() {
        String userId = userSupport.getCurrentUserId();
        User user = userService.getUser(userId);
        return ResultUtils.success(user);
    }

    @PostMapping("/register")
    public BaseResponse<Integer> saveUser(@RequestBody User user) {
        String userId = user.getUserId();
        String password = user.getPassword();
        String userName = user.getUserName();
        Integer userSex = user.getUserSex();
        if (StringUtils.isAnyBlank(userId, password, userName) || password == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不可为空");
        }
        if (password.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度过短，应大于6位");
        }
        Integer result = userService.saveUser(userId, password, userName, userSex);
        if (result.equals(1)) {
            return ResultUtils.success(result);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库操作失败，新增用户失败");
        }
    }

    @GetMapping("/rsa-pks")
    public BaseResponse<String> getRsaPublicKey() {
        String publicKeyStr = RSAUtil.getPublicKeyStr();
        return ResultUtils.success(publicKeyStr);
    }
}
