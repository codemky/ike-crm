package com.ike.pojo;

public class ProductFile {
    private Long id;

    private Long productId;

    private Byte productFileType;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Byte getProductFileType() {
        return productFileType;
    }

    public void setProductFileType(Byte productFileType) {
        this.productFileType = productFileType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}