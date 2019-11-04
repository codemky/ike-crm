package com.ike.service;

import com.ike.pojo.ProductFile;

import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/10 9:20
 * @Version 1.0
 */
public interface ProductFileService {

    boolean save(Long id, List<String> lists);

    /**
     * type: 0 删除产品所有文件  1 删除产品图片 2 删除产品文档
     * @param id
     * @param type
     * @return
     */
    boolean delete(Long id,Integer type);

    List<ProductFile> listByProId(Long id,Integer type);

    List<ProductFile> listAll();

    boolean update(Long id, List<String> lists, Integer type);
}
