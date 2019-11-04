package com.ike.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ike.pojo.Product;

import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/22 16:26
 * @Version 1.0
 */
public class ProductExtVO extends Product {

    //销售单位名称
    @ExcelProperty(index = 4, value = "单位")
    private String saleUnitName;
    //产品类型名称
    @ExcelProperty(index = 6, value = "产品类型")
    private String productClassName;

    @ExcelProperty(index = 5, value = "销售状态")
    private String onSaleName;

    @ExcelProperty(index = 7, value = "上市时间")
    private String strTtm;
    //产品文件
    private List<String> Piclists;
    private List<String> Textlists;

    public String getSaleUnitName() {
        return saleUnitName;
    }

    public void setSaleUnitName(String saleUnitName) {
        this.saleUnitName = saleUnitName;
    }

    public String getProductClassName() {
        return productClassName;
    }

    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }

    public String getOnSaleName() {
        return onSaleName;
    }

    public void setOnSaleName(String onSaleName) {
        this.onSaleName = onSaleName;
    }

    public String getStrTtm() {
        return strTtm;
    }

    public void setStrTtm(String strTtm) {
        this.strTtm = strTtm;
    }

    public List<String> getPiclists() {
        return Piclists;
    }

    public void setPiclists(List<String> piclists) {
        Piclists = piclists;
    }

    public List<String> getTextlists() {
        return Textlists;
    }

    public void setTextlists(List<String> textlists) {
        Textlists = textlists;
    }
}
