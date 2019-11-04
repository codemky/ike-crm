package com.ike.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.time.LocalDateTime;
import java.util.List;

public class Product extends BaseRowModel {
    private Long id;

    private Long productClassId;

    private Long saleUnitId;

    private Byte onSale;

    @ExcelProperty(index = 0, value = "产品名称")
    private String productName;

    @ExcelProperty(index = 1, value = "产品型号")
    private String productType;

    @ExcelProperty(index = 2, value = "销售单价")
    private Double salePrice;

    @ExcelProperty(index = 3, value = "成本")
    private Double cost;

    private LocalDateTime ttm;

    @ExcelProperty(index = 8, value = "介绍")
    private String introduction;

    private Long createUserId;

    private LocalDateTime createTime;

    private Long modifyUserId;

    private LocalDateTime modifyTime;

    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductClassId() {
        return productClassId;
    }

    public void setProductClassId(Long productClassId) {
        this.productClassId = productClassId;
    }

    public Long getSaleUnitId() {
        return saleUnitId;
    }

    public void setSaleUnitId(Long saleUnitId) {
        this.saleUnitId = saleUnitId;
    }

    public Byte getOnSale() {
        return onSale;
    }

    public void setOnSale(Byte onSale) {
        this.onSale = onSale;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDateTime getTtm() {
        return ttm;
    }

    public void setTtm(LocalDateTime ttm) {
        this.ttm = ttm;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}