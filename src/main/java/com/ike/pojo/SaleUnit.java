package com.ike.pojo;

public class SaleUnit {
    private Long id;

    private String saleUnitName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleUnitName() {
        return saleUnitName;
    }

    public void setSaleUnitName(String saleUnitName) {
        this.saleUnitName = saleUnitName == null ? null : saleUnitName.trim();
    }
}