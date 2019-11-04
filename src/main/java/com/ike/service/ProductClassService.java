package com.ike.service;

import com.ike.pojo.ProductClass;
import com.ike.pojo.vo.ProductClassVO;

import java.util.List;

/**
 * 产品类别
 * @Author wuchuxin
 * @Date 2019/10/9 9:20
 * @Version 1.0
 */
public interface ProductClassService {
    boolean save(ProductClass productClass);

    boolean delete(Long id);

    List<ProductClassVO> listAll();

    List<ProductClass> listByPreId(Long id);

    String listById(Long id);

    List<ProductClass> listByName(String name);

    boolean update(ProductClass productClass);
}
