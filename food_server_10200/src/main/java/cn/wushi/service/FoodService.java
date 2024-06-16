package cn.wushi.service;

import cn.wushi.po.FoodVo;
import java.util.List;

public interface FoodService {
    public List<FoodVo> listFoodByBusinessId(Integer businessId);
}