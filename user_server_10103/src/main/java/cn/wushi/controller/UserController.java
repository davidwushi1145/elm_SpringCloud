package cn.wushi.controller;

import cn.wushi.po.CommonResult;
import cn.wushi.po.User;
import cn.wushi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/Login")
    public CommonResult<User> getUserByIdByPass(User user) throws Exception {
        return new CommonResult(200, "success", userService.getUserByIdByPass(user));
    }

    @GetMapping("/UserId")
    public CommonResult<Integer> getUserById(User user) throws Exception {
        int result = userService.getUserById(user.getUserId());
        return new CommonResult(200, "success", result);
    }

    @PostMapping("/UserId")
    public CommonResult<Integer> saveUser(User user) throws Exception {
        int result = userService.saveUser(user);
        return new CommonResult(200, "success", result);
    }
}