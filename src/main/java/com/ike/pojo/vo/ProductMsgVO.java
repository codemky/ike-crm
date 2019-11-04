package com.ike.pojo.vo;

import com.ike.pojo.Product;
import com.ike.pojo.ProductFile;

import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/20 13:40
 * @Version 1.0
 */
public class ProductMsgVO {
    private ProductExtVO productExtVO;
    private List<ProductFile> productPicFile;
    private List<ProductFile> productTextFile;

    public ProductExtVO getProductExtVO() {
        return productExtVO;
    }

    public void setProductExtVO(ProductExtVO productExtVO) {
        this.productExtVO = productExtVO;
    }

    public List<ProductFile> getProductPicFile() {
        return productPicFile;
    }

    public void setProductPicFile(List<ProductFile> productPicFile) {
        this.productPicFile = productPicFile;
    }

    public List<ProductFile> getProductTextFile() {
        return productTextFile;
    }

    public void setProductTextFile(List<ProductFile> productTextFile) {
        this.productTextFile = productTextFile;
    }
}
