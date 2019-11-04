package com.ike.service.impl;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.mapper.CustomerMapper;
import com.ike.mapper.CustomerStageLogMapper;
import com.ike.mapper.CustomerStageMapper;
import com.ike.mapper.RelationMapper;
import com.ike.mapper.ext.*;
import com.ike.pojo.*;
import com.ike.pojo.vo.*;
import com.ike.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ClassName CustomerServiceImpl
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/10 11:02
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerExtMapper customerExtMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private CustomerStageLogMapper customerStageLogMapper;
    @Autowired
    private FollowExtMapper followExtMapper;
    @Autowired
    private FollowPlanExtMapper planExtMapper;
    @Autowired
    private FollowArrangeExtMapper arrangeExtMapper;
    @Autowired
    private RelationExtMapper relationExtMapper;


    @Override
    public IPage<CustomerListVo> selectListVoByCriteria(Page page, CustomerSearchVo criteria) {

        Double orderMaxSum = criteria.getOrderMaxSum();
        Long orderMaxCount = criteria.getOrderMaxCount();
        Long complainMaxCount = criteria.getComplainMaxCount();
        Double orderMinSum = criteria.getOrderMinSum();
        Long orderMinCount = criteria.getOrderMinCount();
        Long complainMinCount = criteria.getComplainMinCount();
        Long followMinCount = criteria.getFollowMinCount();
        Long followMaxCount = criteria.getFollowMaxCount();
        List<Long> customerOrder = null;
        List<Long> customerComplain = null;
        List<Long> customerFollow = null;
        //搜索符合订单条件的
        if (orderMaxSum != null || orderMinSum != null || orderMaxCount != null || orderMinCount != null) {
            if (orderMinSum != null || orderMaxSum != null) {
                if (orderMinSum == null) {
                    criteria.setOrderMinSum(orderMaxSum);
                    criteria.setOrderMaxSum(Double.MAX_VALUE);
                }
                if (orderMaxSum == null) {
                    criteria.setOrderMinSum(0.0);
                    criteria.setOrderMaxSum(orderMinSum);
                }
            }
            if (orderMinCount != null || orderMaxCount != null) {
                if (orderMaxCount == null) {
                    criteria.setOrderMinCount(0L);
                    criteria.setOrderMaxCount(orderMinCount);
                }
                if (orderMinCount == null) {
                    criteria.setOrderMinCount(orderMaxCount);
                    criteria.setOrderMaxCount(Long.MAX_VALUE);
                }
            }
            customerOrder = customerExtMapper.selectIdsByOrderBase(criteria.getOrderMinSum(), criteria.getOrderMaxSum(),
                    criteria.getOrderMinCount(), criteria.getOrderMaxCount()).stream().map(Customer::getId).collect(Collectors.toList());
        }
        //搜索符合投诉条件的
        if (complainMinCount != null || complainMaxCount != null) {
            if (complainMinCount == null) {
                criteria.setComplainMinCount(complainMaxCount);
                criteria.setComplainMaxCount(Long.MAX_VALUE);
            }
            if (complainMaxCount == null) {
                criteria.setComplainMinCount(0L);
                criteria.setComplainMaxCount(complainMinCount);
            }
            customerComplain = customerExtMapper.selectIdsByComplain(criteria.getComplainMinCount(), criteria.getComplainMaxCount())
                    .stream().map(Customer::getId).collect(Collectors.toList());
        }
        //搜索符合跟进条件的
        if (followMinCount != null || followMaxCount != null || criteria.getIsFollowed() != null || criteria.getDifferFollow() != null) {
            if (followMinCount == null) {
                criteria.setFollowMinCount(followMaxCount);
                criteria.setFollowMaxCount(Long.MAX_VALUE);
            }
            if (followMaxCount == null) {
                criteria.setFollowMinCount(0L);
                criteria.setFollowMaxCount(followMinCount);
            }
            customerFollow = customerExtMapper.selectIdsByFollow(criteria.getFollowMinCount(), criteria.getFollowMaxCount(),
                    criteria.getDifferFollow(), criteria.getIsFollowed()).stream().map(Customer::getId).collect(Collectors.toList());
        }

        //说明两种条件都有查 则求两次查找的id交集
        if (customerOrder != null && customerComplain != null) {
            criteria.setIds(customerOrder.stream().filter(customerComplain::contains).collect(Collectors.toList()));
        }
        //只有其中一种条件有查询
        if (customerOrder == null && customerComplain != null) {
            criteria.setIds(customerComplain);
        }
        if (customerOrder != null && customerComplain == null) {
            criteria.setIds(customerOrder);
        }

        //如果有查询跟进条件，则求交集
        if (customerFollow != null) {
            if (criteria.getIds() != null) {
                criteria.setIds(criteria.getIds().stream().filter(customerFollow::contains).collect(Collectors.toList()));
            }
            criteria.setIds(customerFollow);
        }

        //如果查找的id为空（集合大小为0） 则说明没有符合条件的记录
        if (criteria.getIds() != null && criteria.getIds().size() == 0) {
            criteria.setFlag(false);
        }

        page.setOptimizeCountSql(false);
        return customerExtMapper.selectListVoByCriteria(page, criteria);
    }

    @Override
    public int countOnElse(String propertyName, Long id) {
        //属性名和id不能空
        if (propertyName == null || id == null) {
            return -1;
        }

        CustomerExample customerExample = new CustomerExample();
        switch (propertyName) {
            case "等级":
                customerExample.createCriteria().andCustomerLevelIdEqualTo(id);
                break;
            case "来源":
                customerExample.createCriteria().andCustomerOriginIdEqualTo(id);
                break;
            case "阶段":
                customerExample.createCriteria().andCustomerStageIdEqualTo(id);
            default:
        }
        return customerMapper.countByExample(customerExample);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importExcel(List<Customer> customerList, List<Relation> relationList) {

        customerExtMapper.importExcel(customerList);
        for (int i = 0; i < customerList.size(); i++) {
            relationList.get(i).setCustomerId(customerList.get(i).getId());
        }
        relationExtMapper.importExcel(relationList);

    }

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Customer selectById(Long id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public CustomerDetailVo selectDetail(Long cid) {
        CustomerDetailVo detailVo = customerExtMapper.selectDetail(cid);
        CustomerDetailVo follow = followExtMapper.selectCustomerDetail(cid);
        Long pCount = planExtMapper.selectCustomerPlanCount(cid);
        Long arCount = arrangeExtMapper.selectCustomerArrangeCount(cid);
        CustomerDetailVo orderInfo = customerExtMapper.selectOrderInfo(cid);
        CustomerDetailVo returnInfo = customerExtMapper.selectReturnInfo(cid);
        CustomerDetailVo refundInfo = customerExtMapper.selectRefundInfo(cid);
        Long cpCount = customerExtMapper.countComplain(cid);

        detailVo.setFollowCount(follow.getFollowCount());
        detailVo.setLastFollowTime(follow.getLastFollowTime());
        detailVo.setFollowPlanCount(pCount);
        detailVo.setFollowArrangeCount(arCount);
        detailVo.setOrderCount(orderInfo.getOrderCount());
        detailVo.setOrderSum(orderInfo.getOrderSum());
        detailVo.setCostSum(orderInfo.getCostSum());
        detailVo.setReturnCount(returnInfo.getReturnCount());
        detailVo.setReturnSum(returnInfo.getReturnSum());
        detailVo.setRefundCount(refundInfo.getRefundCount());
        detailVo.setRefundSum(refundInfo.getRefundSum());
        detailVo.setComplainCount(cpCount);
        detailVo.setDebt(detailVo.getOrderSum() - detailVo.getRefundSum());
        detailVo.setProfit(detailVo.getOrderSum() - detailVo.getCostSum());

//        BeanUtils.copyProperties(follow,detailVo);
//        BeanUtils.copyProperties(orderInfo,detailVo);
//        BeanUtils.copyProperties(returnInfo,detailVo);
//        BeanUtils.copyProperties(refundInfo,detailVo);

        return detailVo;
    }

    @Override
    public CustomerVo selectForUpdate(Long cid) {
        return customerExtMapper.selectForUpdate(cid);
    }

    @Override
    public int update(Customer customer) {
        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public int create(Customer customer) {
        return customerMapper.insertSelective(customer);
    }

    @Override
    public int transferCustomer(Long before, Long after) {
        Customer customer = new Customer();
        customer.setEmployeeId(after);
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andEmployeeIdEqualTo(before);
        return customerMapper.updateByExampleSelective(customer, customerExample);
    }

    @Override
    public List<SelectVo> selectIdAndNameAll() {
        return customerExtMapper.selectIdAndNameAll();
    }

    @Override
    public List<StatisticsVo> statisticalCountByShowType(int showType) {
        List<StatisticsVo> statisticsVoList = null;

        switch (showType) {
            //根据用户阶段统计
            case 1:
                statisticsVoList = statisticsMapper.selectCustomerCountByStageName();
                break;
            //根据用户等级统计
            case 2:
                statisticsVoList = statisticsMapper.selectCustomerCountByLevelName();
                break;
            //根据用户来源统计
            case 3:
                statisticsVoList = statisticsMapper.selectCustomerCountByOrigin();
                break;
            //根据用户地址统计
            case 4:
                statisticsVoList = statisticsMapper.selectCustomerCountByAddress();
                break;
            default:
                //没有则返回空集合
                break;
        }

        return statisticsVoList;
    }

    @Override
    public int deleteCustomerById(Long id) {
        int i = customerMapper.deleteByPrimaryKey(id);
        RelationExample example = new RelationExample();
        example.createCriteria().andCustomerIdEqualTo(id);
        int i1 = relationMapper.deleteByExample(example);
        CustomerStageLogExample stageLogExample = new CustomerStageLogExample();
        stageLogExample.createCriteria().andCustomerIdEqualTo(id);
        int i2 = customerStageLogMapper.deleteByExample(stageLogExample);
        return i + i1 + i2;
    }
}
