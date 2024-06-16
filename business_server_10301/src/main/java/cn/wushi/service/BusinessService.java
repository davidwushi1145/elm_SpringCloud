package cn.wushi.service;

import cn.wushi.po.BusinessVo;

import java.util.List;

public interface BusinessService {
    public List<BusinessVo> listBusinessByOrderTypeId(Integer orderTypeId);

    public BusinessVo getBusinessById(Integer businessId);

    public List<BusinessVo> listBusinessByBusinessName(String businessName);

    public List<BusinessVo> listBusiness();
}



