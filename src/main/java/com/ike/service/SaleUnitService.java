package com.ike.service;

import com.ike.pojo.SaleUnit;

import java.util.List;

/**
 * 产品销售单位
 * @Author wuchuxin
 * @Date 2019/10/9 9:53
 * @Version 1.0
 */
public interface SaleUnitService {

    boolean save(SaleUnit saleUnit);

    boolean delete(Long id);

    List<SaleUnit> listAll();

    SaleUnit listById(Long id);

    List<SaleUnit> listByName(String name);

    boolean update(SaleUnit saleUnit);
}
