package com.ike.pojo.vo;

import com.ike.common.util.ServletBeanUtil;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wuchuxin
 * @Date 2019/10/15 8:50
 * @Version 1.0
 */
public class ProductSearchVO {
    //有销售记录的
    private Integer isSold;


    //录入时间
    private String beginCreateTime;
    private String endCreateTime;

    //成交数量
    private Integer orderNumMin;
    private Integer orderNumMax;

    //成交次数
    private Integer orderTimesMin;
    private Integer orderTimesMax;

    //成交总额度
    private Double orderCountMin;
    private Double orderCountMax;

    //成交总利润
    private Double orderProfitMin;
    private Double orderProfitMax;

    //录入人
    private String createUserName;

    //产品分类
    private Long productClassId;

    //产品名称
    private String productName;

    //销售单价
    private Double salePriceMin;
    private Double salePriceMax;

    //成本
    private Double costMin;
    private Double costMax;

    //单位
    private List<Long> saleUnitId;

    //销售状态
    private Byte onSale;

    //上市时间
    private String beginTtm;
    private String endTtm;

    //排序列名
    private String sortName;

    //排序方式
    private String sortType;

    public static ProductSearchVO getProperties(HttpServletRequest request, ProductSearchVO searchVO){
        ServletBeanUtil.populate(searchVO, request);

        String[] s = request.getParameterValues("saleUnitId");
        if (s != null) {
            for (String s1 : s) {
                if (s1 != null && s1.length() != 0) {
                    List<Long> saleUnitIds = Arrays.stream(request.getParameterValues("saleUnitId"))
                            .map(Long::parseLong).collect(Collectors.toList());
                    searchVO.setSaleUnitId(saleUnitIds);
                }
            }
        }

        if (searchVO.getIsSold() == null) {
            searchVO.setIsSold(-1);
        }

        //默认为id排序
        if (searchVO.getSortName() == null) {
            searchVO.setSortName("t.product_name");
        }
        //默认为升序
        if (searchVO.getSortType() == null) {
            searchVO.setSortType("ASC");
        }
        return searchVO;
    }

    public Integer getIsSold() {
        return isSold;
    }

    public void setIsSold(Integer isSold) {
        this.isSold = isSold;
    }

    public String getBeginCreateTime() {
        return beginCreateTime;
    }

    public void setBeginCreateTime(String beginCreateTime) {
        this.beginCreateTime = beginCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Integer getOrderNumMin() {
        return orderNumMin;
    }

    public void setOrderNumMin(Integer orderNumMin) {
        this.orderNumMin = orderNumMin;
    }

    public Integer getOrderNumMax() {
        return orderNumMax;
    }

    public void setOrderNumMax(Integer orderNumMax) {
        this.orderNumMax = orderNumMax;
    }

    public Integer getOrderTimesMin() {
        return orderTimesMin;
    }

    public void setOrderTimesMin(Integer orderTimesMin) {
        this.orderTimesMin = orderTimesMin;
    }

    public Integer getOrderTimesMax() {
        return orderTimesMax;
    }

    public void setOrderTimesMax(Integer orderTimesMax) {
        this.orderTimesMax = orderTimesMax;
    }

    public Double getOrderCountMin() {
        return orderCountMin;
    }

    public void setOrderCountMin(Double orderCountMin) {
        this.orderCountMin = orderCountMin;
    }

    public Double getOrderCountMax() {
        return orderCountMax;
    }

    public void setOrderCountMax(Double orderCountMax) {
        this.orderCountMax = orderCountMax;
    }

    public Double getOrderProfitMin() {
        return orderProfitMin;
    }

    public void setOrderProfitMin(Double orderProfitMin) {
        this.orderProfitMin = orderProfitMin;
    }

    public Double getOrderProfitMax() {
        return orderProfitMax;
    }

    public void setOrderProfitMax(Double orderProfitMax) {
        this.orderProfitMax = orderProfitMax;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Long getProductClassId() {
        return productClassId;
    }

    public void setProductClassId(Long productClassId) {
        this.productClassId = productClassId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getSalePriceMin() {
        return salePriceMin;
    }

    public void setSalePriceMin(Double salePriceMin) {
        this.salePriceMin = salePriceMin;
    }

    public Double getSalePriceMax() {
        return salePriceMax;
    }

    public void setSalePriceMax(Double salePriceMax) {
        this.salePriceMax = salePriceMax;
    }

    public Double getCostMin() {
        return costMin;
    }

    public void setCostMin(Double costMin) {
        this.costMin = costMin;
    }

    public Double getCostMax() {
        return costMax;
    }

    public void setCostMax(Double costMax) {
        this.costMax = costMax;
    }

    public List<Long> getSaleUnitId() {
        return saleUnitId;
    }

    public void setSaleUnitId(List<Long> saleUnitId) {
        this.saleUnitId = saleUnitId;
    }

    public Byte getOnSale() {
        return onSale;
    }

    public void setOnSale(Byte onSale) {
        this.onSale = onSale;
    }

    public String getBeginTtm() {
        return beginTtm;
    }

    public void setBeginTtm(String beginTtm) {
        this.beginTtm = beginTtm;
    }

    public String getEndTtm() {
        return endTtm;
    }

    public void setEndTtm(String endTtm) {
        this.endTtm = endTtm;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
