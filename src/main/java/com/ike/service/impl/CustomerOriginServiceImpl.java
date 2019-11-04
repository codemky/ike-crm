package com.ike.service.impl;

import com.ike.mapper.CustomerOriginMapper;
import com.ike.pojo.CustomerOrigin;
import com.ike.pojo.CustomerOriginExample;
import com.ike.service.CustomerOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName CustomerOriginServiceImpl
 * Description TODO
 *
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 16:13
 **/
@Service
public class CustomerOriginServiceImpl implements CustomerOriginService {

    @Autowired
    private CustomerOriginMapper customerOriginMapper;

    @Override
    public List<CustomerOrigin> selectAll() {
        return customerOriginMapper.selectByExample(null);
    }

    @Override
    public int insert(CustomerOrigin customerOrigin) {
        return customerOriginMapper.insertSelective(customerOrigin);
    }

    @Override
    public int update(CustomerOrigin customerOrigin) {
        return customerOriginMapper.updateByPrimaryKeySelective(customerOrigin);
    }

    @Override
    public int deleteById(Long id) {
        return customerOriginMapper.deleteByPrimaryKey(id)  ;
    }

    @Override
    public CustomerOrigin selectById(Long id) {
        return customerOriginMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CustomerOrigin> selectByName(String name) {
        CustomerOriginExample customerOriginExample = new CustomerOriginExample();
        customerOriginExample.createCriteria().andOriginNameEqualTo(name);
        return customerOriginMapper.selectByExample(customerOriginExample);
    }
}
