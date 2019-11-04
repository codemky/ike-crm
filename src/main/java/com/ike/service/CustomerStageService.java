package com.ike.service;

import com.ike.pojo.CustomerStage;

import java.util.List;

/**
 * InterfaceName CustomerStageService
 * Description TODO
 *
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 17:10
 **/
public interface CustomerStageService {
    
    /**
     * Author: mokuanyuan
     * @since:  2019/10/9 9:13
     */
    List<CustomerStage> selectAll();

    int insert(CustomerStage customerStage);

    int update(CustomerStage customerStage);

    int deleteById(Long id);

    CustomerStage selectById(Long id);

    /**
     * Author: mokuanyuan
     *
     * @param name 阶段名称
     * @return list 符合名称的阶段记录
     * @apiNote: 根据名称查询相应的阶段记录
     * @since: 2019/10/22 14:52
     */
    List<CustomerStage> selectByName(String name);
}
