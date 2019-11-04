package com.ike.service.impl;

import com.ike.mapper.ProductClassMapper;
import com.ike.pojo.ProductClass;
import com.ike.pojo.ProductClassExample;
import com.ike.pojo.vo.ProductClassVO;
import com.ike.service.ProductClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/9 9:28
 * @Version 1.0
 */
@Service
public class ProductClassServiceImpl implements ProductClassService {

    @Autowired
    private ProductClassMapper productClassMapper;

    @Override
    public boolean save(ProductClass productClass) {
        return productClassMapper.insert(productClass) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return productClassMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<ProductClassVO> listAll() {
        return findChildren((long) 0);
    }

    public List<ProductClassVO> findChildren(Long preId) {
        if (this.listByPreId(preId) == null) {
            return null;
        }
        List<ProductClass> productClasses = this.listByPreId(preId);
        List<ProductClassVO> productClassVOS = new ArrayList<>();
        for (ProductClass productClass : productClasses) {
            ProductClassVO productClassVO = new ProductClassVO();
            productClassVO.setLabel(productClass.getClassName());
            productClassVO.setValue(productClass.getId());
            productClassVO.setChildren(findChildren(productClass.getId()));
            productClassVOS.add(productClassVO);
        }
        return productClassVOS;
    }

    @Override
    public List<ProductClass> listByPreId(Long id) {
        ProductClassExample example = new ProductClassExample();
        ProductClassExample.Criteria criteria = example.createCriteria();
        criteria.andPreviousClassIdEqualTo(id);
        return productClassMapper.selectByExample(example);
    }

    @Override
    public String listById(Long id) {
        ProductClass productClass = productClassMapper.selectByPrimaryKey(id);
        ProductClass productClass1 = productClass;
        String className = productClass.getClassName();
        while (productClass1.getPreviousClassId() != 0) {
            productClass1 = productClassMapper.selectByPrimaryKey(productClass1.getPreviousClassId());
            className = productClass1.getClassName() + "-" + className;
        }
        return className;
    }

    @Override
    public List<ProductClass> listByName(String name) {
        ProductClassExample example = new ProductClassExample();
        ProductClassExample.Criteria criteria = example.createCriteria();
        criteria.andClassNameEqualTo(name);
        return productClassMapper.selectByExample(example);
    }

//    @Override
//    public List<ProductClass> listAll() {
//        return productClassMapper.selectByExample(null);
//    }

    @Override
    public boolean update(ProductClass productClass) {
        return productClassMapper.updateByPrimaryKey(productClass) > 0;
    }


}
