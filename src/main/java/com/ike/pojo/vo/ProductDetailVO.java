package com.ike.pojo.vo;

import com.ike.pojo.Product;
import com.ike.pojo.ProductFile;

import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/11 16:51
 * @Version 1.0
 */
public class ProductDetailVO {
    private ProductVO productVO;
    private ProductExtVO productExtVO;
    private List<ProductFile> productFile;

    public ProductVO getProductVO() {
        return productVO;
    }

    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }

    public ProductExtVO getProductExtVO() {
        return productExtVO;
    }

    public void setProductExtVO(ProductExtVO productExtVO) {
        this.productExtVO = productExtVO;
    }

    public List<ProductFile> getProductFile() {
        return productFile;
    }

    public void setProductFile(List<ProductFile> productFile) {
        this.productFile = productFile;
    }

}
