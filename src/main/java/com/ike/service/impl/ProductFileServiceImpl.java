package com.ike.service.impl;

import com.ike.mapper.ProductFileMapper;
import com.ike.pojo.ProductFile;
import com.ike.pojo.ProductFileExample;
import com.ike.service.ProductFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wuchuxin
 * @Date 2019/10/10 9:24
 * @Version 1.0
 */
@Service
public class ProductFileServiceImpl implements ProductFileService {

    @Autowired
    private ProductFileMapper productFileMapper;

    @Override
    public boolean save(Long id, List<String> lists) {
        if(lists == null || id == null){
            return false;
        }
        ProductFile productFile = new ProductFile();
        for (String list : lists) {
            productFile.setUrl(list);
            productFile.setProductId(id);
            productFile.setProductFileType((byte) 1);
            productFileMapper.insert(productFile);
        }
        return true;
    }

    @Override
    public boolean delete(Long id,Integer type) {
        ProductFileExample example = new ProductFileExample();
        ProductFileExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(id);
        if(type == 1){
            criteria.andProductFileTypeEqualTo((byte) 1);
        }else if(type == 2){
            criteria.andProductFileTypeEqualTo((byte) 2);
        }else if(type >2 || type<0){
            System.out.println("删除文件类型错误");
            return false;
        }
        return productFileMapper.deleteByExample(example) > 0;
    }

    @Override
    public List<ProductFile> listByProId(Long id,Integer type) {
        ProductFileExample example = new ProductFileExample();
        ProductFileExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(id);
        if(type == 1){
            criteria.andProductFileTypeEqualTo((byte) 1);
        }else if(type == 2){
            criteria.andProductFileTypeEqualTo((byte) 2);
        }else if(type >2 || type<0){
            System.out.println("选择文件类型错误");
            return null;
        }
        return productFileMapper.selectByExample(example);
    }

    @Override
    public List<ProductFile> listAll() {
        return productFileMapper.selectByExample(null);
    }

    @Override
    public boolean update(Long id, List<String> lists, Integer type) {
        if (this.delete(id, type)) {
            return this.save(id,lists);
        } else{
            return false;
        }
    }
}
