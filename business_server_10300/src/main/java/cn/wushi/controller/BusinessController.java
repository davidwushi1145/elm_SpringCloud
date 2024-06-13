package cn.wushi.controller;

import cn.wushi.po.Business;
import cn.wushi.po.CommonResult;
import cn.wushi.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RefreshScope
@RestController
@RequestMapping("/Business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();//这里感觉用不到

    @GetMapping("/OrderTypeId")
    public CommonResult<List> listBusinessByOrderTypeId(Business business) throws Exception {
        return new CommonResult<List>(200, "success",
                businessService.listBusinessByOrderTypeId(business.getOrderTypeId()));
    }

    @GetMapping("/BusinessId")
    public CommonResult<Business> getBusinessById(Business business) throws Exception {
        return new CommonResult<Business>(200, "success",
                businessService.getBusinessById(business.getBusinessId()));
    }
}
