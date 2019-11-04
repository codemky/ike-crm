package com.ike.service.impl;

import com.ike.mapper.CustomerStageMapper;
import com.ike.pojo.CustomerStage;
import com.ike.pojo.CustomerStageExample;
import com.ike.service.CustomerStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName CustomerStageServiceImpl
 * Description TODO
 *
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 17:14
 **/
@Service
public class CustomerStageServiceImpl implements CustomerStageService {

    @Autowired
    private CustomerStageMapper customerStageMapper;

    @Override
    public List<CustomerStage> selectAll() {
        return customerStageMapper.selectByExample(null);
    }

    @Override
    public int insert(CustomerStage customerStage) {
        return customerStageMapper.insertSelective(customerStage);
    }

    @Override
    public int update(CustomerStage customerStage) {
        return customerStageMapper.updateByPrimaryKeySelective(customerStage);
    }

    @Override
    public int deleteById(Long id) {
        return customerStageMapper.deleteByPrimaryKey(id)  ;
    }

    @Override
    public CustomerStage selectById(Long id) {
        return customerStageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CustomerStage> selectByName(String name) {
        CustomerStageExample customerStageExample = new CustomerStageExample();
        customerStageExample.createCriteria().andStageNameEqualTo(name);
        return customerStageMapper.selectByExample(customerStageExample);
    }
}
