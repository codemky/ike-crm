package com.ike.pojo.vo;

import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/20 19:47
 * @Version 1.0
 */
public class ProductClassVO {
    //产品名
    private String label;
    //产品id
    private Long value;
    //子类
    private List<ProductClassVO> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public List<ProductClassVO> getChildren() {
        return children;
    }

    public void setChildren(List<ProductClassVO> children) {
        this.children = children;
    }
}