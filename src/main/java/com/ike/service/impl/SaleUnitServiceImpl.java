package com.ike.service.impl;

import com.ike.mapper.SaleUnitMapper;
import com.ike.pojo.SaleUnit;
import com.ike.pojo.SaleUnitExample;
import com.ike.service.SaleUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/9 9:55
 * @Version 1.0
 */
@Service
public class SaleUnitServiceImpl implements SaleUnitService {

    @Autowired
    private SaleUnitMapper saleUnitMapper;

    @Override
    public boolean save(SaleUnit saleUnit) {
        return saleUnitMapper.insert(saleUnit) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return saleUnitMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<SaleUnit> listAll() {
        return saleUnitMapper.selectByExample(null);
    }

    @Override
    public SaleUnit listById(Long id) {
        return saleUnitMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SaleUnit> listByName(String name) {
        SaleUnitExample example = new SaleUnitExample();
        SaleUnitExample.Criteria criteria = example.createCriteria();
        criteria.andSaleUnitNameEqualTo(name);
        return saleUnitMapper.selectByExample(example);
    }

    @Override
    public boolean update(SaleUnit saleUnit) {
        return saleUnitMapper.updateByPrimaryKey(saleUnit) > 0;
    }
}
