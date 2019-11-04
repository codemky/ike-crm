package com.ike.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.Product;
import com.ike.pojo.vo.*;

import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/9 10:59
 * @Version 1.0
 */
public interface ProductService {

    /**
     * 批量添加产品
     *
     * @param products
     * @return
     */
    boolean insertList(List<ProductExtVO> products);

    boolean save(Product product);

    ProductExtVO listById(Long id);

    /**
     * 查询重名产品
     *
     * @param name
     * @param type
     * @return
     */
    List<Product> selectByNameAndType(String name, String type);

    /**
     * 分页查找产品列表
     * @param page
     * @return
     */
    IPage<ProductVO> listAll(Page<ProductVO> page);

    /**
     * 按条件分页查找产品列表
     * @param page
     * @param searchVO
     * @return
     */
    IPage<ProductVO> listAllBySelect(Page<ProductVO> page, ProductSearchVO searchVO);

    IPage<TradedProductVO> listTradedProducts(Page<TradedProductVO> page,Long id);

    ProductVO listDetailById(Long id);

    ProductVO selectCustomerTimes(Long id);

    /**
     * 动态查找统计信息
     * @param id
     * @param type
     * @param year
     * @param month
     * @return
     */
    List<ProStatisticVO> listByYMD(Long id, String type, Integer year, Integer month);

    List<ProductExtVO> getExcelList();

    /**
     * 逻辑删除
     * @param ids
     * @return
     */
    boolean delete(List<Long> ids);

    boolean update(Product product);

    /**
     * 根据产品ID查询产品信息
     *
     * @params [id]
     * @Return com.ike.pojo.Product
     * @Date 2019/10/25 20:11
     **/
    Product selectById(Long id);
}
