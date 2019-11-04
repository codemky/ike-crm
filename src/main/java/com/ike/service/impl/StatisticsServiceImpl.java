package com.ike.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.mapper.ext.StatisticsMapper;
import com.ike.pojo.ProductClass;
import com.ike.pojo.vo.*;
import com.ike.service.ProductClassService;
import com.ike.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper mapper;
    @Autowired
    private ProductClassService productClassService;

    @Override
    public List<CustomerStatisticVO> staCustomerAdd(String dateType, String date) {
        if ( "month".equals(dateType) ) {
            return mapper.staCustomerAddByMonth(date);
        } else if ( "day".equals(dateType) ) {
            return mapper.staCustomerAddByDay(date);
        } else {
            return mapper.staCustomerAddByYear();
        }
    }

    @Override
    public List<CustomerStatisticVO> staFollowAdd(String dateType, String date) {
        if ( "month".equals(dateType) ) {
            return mapper.staFollowAddByMonth(date);
        } else if ( "day".equals(dateType) ) {
            return mapper.staFollowAddByDay(date);
        } else {
            return mapper.staFollowAddByYear();
        }
    }

    @Override
    public List<CustomerStatisticVO> staCustomerFollow(String dateType, String date) {
        if ( "month".equals(dateType) ) {
            return mapper.staCustomerFollowByMonth(date);
        } else if ( "day".equals(dateType) ) {
            return mapper.staCustomerFollowByDay(date);
        } else {
            return mapper.staCustomerFollowByYear();
        }
    }

    @Override
    public IPage<ProStatisticVO> staProductByName(Page<ProStatisticVO> page, StaProSearchVO searchVO) {
        return mapper.staProductByName(page,searchVO);
    }

    @Override   //searchVO.getPreId();  0找到子类
    public List<StaProductClassVO> staProductByClass(StaProSearchVO searchVO) {
        List<StaProductClassVO> staProductClassVOS = mapper.staProductByClass(searchVO);
        List<StaProductClassVO> productClassVOS = new ArrayList<>();
        for (StaProductClassVO staProductClassVO : staProductClassVOS) {
            StaProductClassVO classVO = new StaProductClassVO();
            classVO.setTotalPrice((double) 0);
            classVO.setCustomerTimes(0);
            classVO.setTotalSum(0);
            if (staProductClassVO.getTotalPrice() == null)
                staProductClassVO.setTotalPrice((double) 0);
            if (staProductClassVO.getCustomerTimes() == null)
                staProductClassVO.setCustomerTimes(0);
            if (staProductClassVO.getTotalSum() == null)
                staProductClassVO.setTotalSum(0);
            if (staProductClassVO.getPreClassId().equals(searchVO.getPreId())) {
                //统计类别所属子类所有数据
                //需要循环
                classVO = findNext(staProductClassVO.getId(), staProductClassVOS, classVO);
                classVO.setClassName(staProductClassVO.getClassName());
                classVO.setId(staProductClassVO.getId());
                classVO.setPreClassId(staProductClassVO.getPreClassId());
                productClassVOS.add(classVO);
            }
        }
        return productClassVOS;
    }

    public StaProductClassVO findNext(Long id, List<StaProductClassVO> staProductClassVOS, StaProductClassVO classVO) {
        //递归条件  该分类没有子类结束递归  保存叶子结点数据
        if (productClassService.listByPreId(id).size() == 0) {
            for (StaProductClassVO staProductClassVO : staProductClassVOS) {
                if (staProductClassVO.getId().equals(id)) {
                    return staProductClassVO;
                }
            }
        }
        //该分类还有子类。 继续递归这个子类
        for (StaProductClassVO staProductClassVO : staProductClassVOS) {
            if (staProductClassVO.getId().equals(id)) {
                if (staProductClassVO.getTotalPrice() == null)
                    staProductClassVO.setTotalPrice((double) 0);
                if (staProductClassVO.getCustomerTimes() == null)
                    staProductClassVO.setCustomerTimes(0);
                if (staProductClassVO.getTotalSum() == null)
                    staProductClassVO.setTotalSum(0);
                classVO.setTotalPrice(classVO.getTotalPrice() + staProductClassVO.getTotalPrice());
                classVO.setTotalSum(classVO.getTotalSum() + staProductClassVO.getTotalSum());
                classVO.setCustomerTimes(classVO.getCustomerTimes() + staProductClassVO.getCustomerTimes());
            }
        }

        for (ProductClass productClass : productClassService.listByPreId(id)) {
            findNext(productClass.getId(), staProductClassVOS, classVO);
        }
        return classVO;
    }

    @Override
    public List<StatisticsVo> staOrderBaseTotalByDateArea(OrderBaseStatisitcVO orderBaseStatisitcVO) {
        return mapper.statisticalOrderBaseTotalByDateArea(orderBaseStatisitcVO);
    }

    @Override
    public List<StatisticsVo> staOrderBaseTotal(OrderBaseStatisitcVO orderBaseStatisitcVO) {
        return mapper.statisticalOrderBaseTotal(orderBaseStatisitcVO);
    }
}
