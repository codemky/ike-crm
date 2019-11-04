package com.ike.service;

import com.ike.pojo.CustomerOrigin;

import java.util.List;

/**
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 16:11
 **/
public interface CustomerOriginService {

    /**
     * Author: mokuanyuan
     * @since:  2019/10/9 9:13
     */
    List<CustomerOrigin> selectAll();

    int insert(CustomerOrigin customerOrigin);

    int update(CustomerOrigin customerOrigin);

    int deleteById(Long id);

    CustomerOrigin selectById(Long id);

    /**
     * Author: mokuanyuan
     *
     * @param name 来源名称
     * @return list 符合名称的来源记录
     * @apiNote: 根据名称查询相应的来源记录
     * @since: 2019/10/22 14:52
     */
    List<CustomerOrigin> selectByName(String name);
}
