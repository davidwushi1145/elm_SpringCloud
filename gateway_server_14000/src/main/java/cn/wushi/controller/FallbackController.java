package cn.wushi.controller;

import cn.wushi.po.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FallbackController {
    @RequestMapping("/fallback")
    public CommonResult fallback() {
        return new CommonResult(403, "Gateway触发了熔断降级", null);
    }
}