package cn.wushi.service.impl;

import cn.wushi.mapper.BusinessMapper;
import cn.wushi.po.Business;
import cn.wushi.po.BusinessVo;
import cn.wushi.service.BusinessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<BusinessVo> listBusinessByOrderTypeId(Integer orderTypeId) {
        try {
            List<Business> businessList = businessMapper.listBusinessByOrderTypeId(orderTypeId);
            return getBusinessVo(businessList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BusinessVo getBusinessById(Integer businessId) {
        try {
            Business business = businessMapper.getBusinessById(businessId);
            return getBusinessVo(business);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BusinessVo> listBusinessByBusinessName(String businessName) {
        try {
            List<Business> businessList = businessMapper.listBusinessByBusinessName(businessName);
            return getBusinessVo(businessList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public BusinessVo getBusinessVo(Business business) {
        if (business == null) {
            return null;
        }
        BusinessVo businessVo = new BusinessVo();
        BeanUtils.copyProperties(business, businessVo);
        return businessVo;
    }

    public List<BusinessVo> listBusiness() {
        try {
            List<Business> businessList = businessMapper.listBusiness();
            return getBusinessVo(businessList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BusinessVo> getBusinessVo(List<Business> businessList) {
        if (CollectionUtils.isEmpty(businessList)) {
            return new ArrayList<>();
        }
        return businessList.stream().map(this::getBusinessVo).collect(Collectors.toList());
    }
}
