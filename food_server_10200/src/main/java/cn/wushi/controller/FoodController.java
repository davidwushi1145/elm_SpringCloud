package cn.wushi.controller;

import cn.wushi.po.CommonResult;
import cn.wushi.po.Food;
import cn.wushi.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/Food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/BusinessId")
    public CommonResult<List> listFoodByBusinessId(Food food) throws Exception {
        List<Food> list = foodService.listFoodByBusinessId(food.getBusinessId());
        return new CommonResult<List>(200, "success", list);
    }
}