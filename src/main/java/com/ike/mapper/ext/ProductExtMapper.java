package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.Product;
import com.ike.pojo.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductExtMapper {

    int insertList(@Param("products") List<ProductExtVO> products);

    //分页查找产品列表
    IPage<ProductVO> listAllByPage(Page<ProductVO> page);

    //多条件分页查找产品列表
    IPage<ProductVO> listAllBySelectByPage(@Param("page") Page<ProductVO> page,@Param("searchVO") ProductSearchVO searchVO);

    //查找产品详情
    ProductVO listDetailById(Long id);

    //查找产品成交客户数
    ProductVO selectCustomerTimes(Long id);

    //统计各年份客户数量、产品成交总额、产品成交数量
    List<ProStatisticVO> selectByYear(@Param("id") Long id);

    //按年份统计各月份客户数量、产品成交总额、产品成交数量
    List<ProStatisticVO> selectMonByYear(@Param("id") Long id, @Param("year") int year);

    //按月份统计各日客户数量、产品成交总额、产品成交数量
    List<ProStatisticVO> selectDayByMon(@Param("id") Long id, @Param("year") int year, @Param("month") int month);

    //分页查找产品成交订单记录
    IPage<TradedProductVO> listTradedProduct(@Param("page") Page<TradedProductVO> page,@Param("id") Long id);

    List<ProductExtVO> getExcelList();

    List<Product> selectByNameAndType(@Param("name") String name, @Param("type") String type);

    //逻辑删除产品
    int updateByDel(@Param("ids") List<Long> ids);

    //修改产品销售状态
    int updateOnSale(@Param("id") Long id,@Param("onSale") Integer onSale);


}