package cn.wushi.service;

import cn.wushi.po.Food;

import java.util.List;

public interface FoodService {
    public List<Food> listFoodByBusinessId(Integer businessId);
}