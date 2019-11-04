package com.ike.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.Customer;
import com.ike.pojo.Relation;
import com.ike.pojo.User;
import com.ike.pojo.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.List;

/**
 * InterfaceName CustomerService
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/10 10:16
 **/
public interface CustomerService {


    /**
     * Author: mokuanyuan
     * @param page 分页参数
     * @param criteria 多重条件对象
     * @return Page
     * @apiNote: 根据多种条件查询客户信息列表
     * @since:  2019/10/13 20:22
     */
    IPage<CustomerListVo> selectListVoByCriteria(Page page, CustomerSearchVo criteria);


    /**
     * Author: mokuanyuan
     * @param page 分页参数
     * @param eid 员工id
     * @return List
     * @apiNote:  根据负责人（员工）id 查询所有的客户列表
     * @since:  2019/10/10 10:56
     */
//    IPage<CustomerListVo> selectCustomerListByEid(Page<CustomerListVo> page, Long eid);

    /**
     * Author: mokuanyuan
     * @param page 分页参数
     * @param ids 员工id数组
     * @return List
     * @apiNote:  根据负责人（员工）id数组 查询所有的客户列表
     * @since:  2019/10/11 20:26
     */
//    IPage<CustomerListVo> selectCustomerListByEmIds(Page<CustomerListVo> page, List<Long> ids);


    /**
     * Author: mokuanyuan
     * @param cid 客户id
     * @return customer
     * @apiNote: 根据客户id获取一条客户记录
     * @since:  2019/10/11 10:59
     */
    public Customer selectById(Long cid);

    /**
     * Author: mokuanyuan
     *
     * @param cid 客户id
     * @return CustomerDetailVo
     * @apiNote: 根据客户id获取客户的详细资料信息
     * @since: 2019/10/21 9:56
     */
    public CustomerDetailVo selectDetail(Long cid);

    /**
     * Author: mokuanyuan
     * @param cid 客户id
     * @return CustomerVo
     * @apiNote: 根据客户id获取客户详情 用于表单回显
     * @since:  2019/10/11 16:59
     */
    public CustomerVo selectForUpdate(Long cid);

    /**
     * Author: mokuanyuan
     * @param customer 更新后的客户对象
     * @return int 影响记录数目
     * @apiNote: 更新客户记录
     * @since:  2019/10/11 10:48
     */
    int update(Customer customer);

    /**
     * Author: mokuanyuan
     *
     * @param customer 客户实体对象
     * @return int 影响的记录数目
     * @apiNote: 添加客户记录
     * @since: 2019/10/14 20:39
     */
    int create(Customer customer);

    /**
     * Author: mokuanyuan
     *
     * @param before 交接前员工id
     * @param after  交接后员工id
     * @return int 受影响的记录数目
     * @apiNote: 把前员工负责的所有客户转移到后员工上
     * @since: 2019/10/14 20:42
     */
    int transferCustomer(Long before, Long after);

    /**
     * Author: mokuanyuan
     *
     * @return List<SelectVo>
     * @apiNote: 获取所有的客户id和客户名称
     * @since: 2019/10/21 11:19
     */
    List<SelectVo> selectIdAndNameAll();

    /**
     * Author: mokuanyuan
     *
     * @param propertyName 属性名
     * @param id           外键id
     * @return int 查询到的记录数
     * @apiNote: 根据属性名（如 等级 来源 阶段）和相应id查询符合条件的记录数
     * @since: 2019/10/15 8:10
     */
    int countOnElse(String propertyName, Long id);

    /**
     * Author: mokuanyuan
     *
     * @param customerList 客户列表
     * @param relationList 客户联系人列表
     * @apiNote: 把客户和客户联系人的Excel表数据导入到数据库
     * @since: 2019/10/22 10:06
     */
    void importExcel(List<Customer> customerList, List<Relation> relationList);

    /**
     * 根据展示类型返回对应的客户数目统计
     * @param showType 展示类型（1.按阶段，2.按等级，3.按来源，4.按所在城市）
     * @return 含有条数和对应字段的数据集合
     */
    List<StatisticsVo> statisticalCountByShowType(int showType);

    /**
     * 根据客户的id进行删除客户信息
     * 并且把relation表中的数据一并删除
     * customer_stage_log中的数据一并删除
     * @param id 客户id
     * @return
     */
    int deleteCustomerById(Long id);

}
