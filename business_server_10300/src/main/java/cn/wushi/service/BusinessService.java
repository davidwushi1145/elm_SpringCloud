package cn.wushi.service;

import java.util.List;

import cn.wushi.po.BusinessVo;

public interface BusinessService {
    public List<BusinessVo> listBusinessByOrderTypeId(Integer orderTypeId);

    public BusinessVo getBusinessById(Integer businessId);

    public List<BusinessVo> listBusinessByBusinessName(String businessName);

    public List<BusinessVo> listBusiness();
}



