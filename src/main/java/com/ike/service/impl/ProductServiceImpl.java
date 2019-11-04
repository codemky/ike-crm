package com.ike.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.mapper.ProductMapper;
import com.ike.mapper.ext.ProductExtMapper;
import com.ike.pojo.Product;
import com.ike.pojo.ProductExample;
import com.ike.pojo.vo.*;
import com.ike.service.ProductClassService;
import com.ike.service.ProductService;
import com.ike.service.SaleUnitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/9 11:16
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductExtMapper productExtMapper;
    @Autowired
    private SaleUnitService saleUnitService;
    @Autowired
    private ProductClassService productClassService;

    @Override
    public boolean insertList(List<ProductExtVO> products) {
        return productExtMapper.insertList(products) > 0;
    }

    @Override
    public boolean save(Product product) {
        return productMapper.insert(product) > 0;
    }

    @Override
    public ProductExtVO listById(Long id) {
        Product product = productMapper.selectByPrimaryKey(id);
        ProductExtVO productVO = new ProductExtVO();
        BeanUtils.copyProperties(product, productVO);
        if (product.getSaleUnitId() != null)
            productVO.setSaleUnitName(saleUnitService.listById(product.getSaleUnitId()).getSaleUnitName());
        if (product.getProductClassId() != null)
            productVO.setProductClassName(productClassService.listById(product.getProductClassId()));
        return productVO;
    }

    @Override
    public List<Product> selectByNameAndType(String name, String type) {
        return productExtMapper.selectByNameAndType(name, type);
    }

    @Override
    public boolean delete(List<Long> ids) {
        return productExtMapper.updateByDel(ids) > 0;
    }

    @Override
    public boolean update(Product product) {
        return productMapper.updateByPrimaryKey(product) > 0;
    }

    @Override
    public Product selectById(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public IPage<ProductVO> listAll(Page<ProductVO> page) {
        return productExtMapper.listAllByPage(page);
    }

    @Override
    public IPage<ProductVO> listAllBySelect(Page<ProductVO> page, ProductSearchVO searchVO) {
        return productExtMapper.listAllBySelectByPage(page,searchVO);
    }

    @Override
    public IPage<TradedProductVO> listTradedProducts(Page<TradedProductVO> page, Long id) {
        return productExtMapper.listTradedProduct(page,id);
    }

    @Override
    public ProductVO listDetailById(Long id) {
        return productExtMapper.listDetailById(id);
    }

    @Override
    public ProductVO selectCustomerTimes(Long id) {
        return productExtMapper.selectCustomerTimes(id);
    }

    @Override
    public List<ProStatisticVO> listByYMD(Long id, String stype, Integer year, Integer month) {
        switch (stype) {
            case "year":
                return productExtMapper.selectByYear(id);
            case "month":
                return productExtMapper.selectMonByYear(id, year);
            case "day":
                return productExtMapper.selectDayByMon(id, year, month);
            case "specify":
                return null;
        }
        return null;
    }

    @Override
    public List<ProductExtVO> getExcelList() {
        return productExtMapper.getExcelList();
    }

}
