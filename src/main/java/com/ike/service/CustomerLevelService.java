package com.ike.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ike.pojo.CustomerLevel;

import java.util.List;

/**
 * @author LonelySeven
 * @date 2019/10/9 8:59
 **/
public interface CustomerLevelService {

    /**
     * Author: mokuanyuan
     * @since:  2019/10/9 9:13
     */
    List<CustomerLevel> selectAll();

    /**
     * Author: mokuanyuan
     * @param customerLevel
     * @return int
     * @apiNote: 新增客户等级信息
     * @since:  2019/10/9 10:20
     */
    int insert(CustomerLevel customerLevel);

    /**
     * Author: mokuanyuan
     * @param customerLevel
     * @return int
     * @apiNote: 更新客户等级信息
     * @since:  2019/10/9 10:20
     */
    int update(CustomerLevel customerLevel);

    /**
     * Author: mokuanyuan
     * @param id
     * @return int
     * @apiNote: 根据主键id删除客户等级记录
     * @since:  2019/10/9 11:08
     */
    int deleteById(Long id);

    /**
     * Author: mokuanyuan
     * @param id
     * @return CustomerLevel
     * @apiNote: 根据id获取客户等级信息
     * @since:  2019/10/9 10:21
     */
    CustomerLevel selectById(Long id);

    /**
     * Author: mokuanyuan
     *
     * @param name 等级名称
     * @return list 符合名称的等级记录
     * @apiNote: 根据名称查询相应的等级记录
     * @since: 2019/10/22 14:52
     */
    List<CustomerLevel> selectByName(String name);



}
