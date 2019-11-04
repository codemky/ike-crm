package com.ike.service.impl;

import com.ike.mapper.CustomerLevelMapper;
import com.ike.pojo.CustomerLevel;
import com.ike.pojo.CustomerLevelExample;
import com.ike.service.CustomerLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName customerLevelServiceImpl
 * Description TODO
 *
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 9:14
 **/
@Service
public class CustomerLevelServiceImpl implements CustomerLevelService {

    @Autowired
    private CustomerLevelMapper customerLevelMapper;


    @Override
    public List<CustomerLevel> selectAll() {
        return customerLevelMapper.selectByExample(null);
    }

    @Override
    public int insert(CustomerLevel customerLevel) {
        return customerLevelMapper.insertSelective(customerLevel) ;
    }

    @Override
    public int update(CustomerLevel customerLevel) {
        return customerLevelMapper.updateByPrimaryKeySelective(customerLevel);
    }

    @Override
    public int deleteById(Long id) {
        return customerLevelMapper.deleteByPrimaryKey(id)  ;
    }

    @Override
    public CustomerLevel selectById(Long id) {
        return customerLevelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CustomerLevel> selectByName(String name) {
        CustomerLevelExample customerLevelExample = new CustomerLevelExample();
        customerLevelExample.createCriteria().andLevelNameEqualTo(name);
        return customerLevelMapper.selectByExample(customerLevelExample);
    }
}
