package com.ike;

import com.ike.common.util.DateUtil;
import com.ike.mapper.OrderBaseMapper;
import com.ike.mapper.ext.OrderBaseExtMapper;
import com.ike.mapper.ext.StatisticsMapper;
import com.ike.pojo.vo.OrderBaseStatisitcVO;
import com.ike.service.CustomerService;
import com.ike.service.OrderBaseService;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Date：2019-10-14 9:05
 * @Description：<描述>
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class StatisTest {

    @Resource
    HttpServletRequest request;
    @Resource
    private StatisticsMapper statisticsMapper;
    @Resource
    private CustomerService customerService;
    @Resource
    private OrderBaseService orderBaseService;
    @Resource
    private OrderBaseExtMapper orderBaseExtMapper;

    @Test
    public void testStatics(){

        System.out.println("===========================阶段"+customerService.statisticalCountByShowType(1).get(0).getDataName());
        System.out.println("===========================等级"+customerService.statisticalCountByShowType(2).get(0).getDataName());
        System.out.println("===========================来源"+customerService.statisticalCountByShowType(3).get(0).getDataName());
        System.out.println("===========================地址"+customerService.statisticalCountByShowType(4).get(0).getDataName());

    }

    @Test
    public void mapperTest(){

        Map<String ,Object> countNameAndState = new HashMap<>();
        countNameAndState.put("order_state",1);
        countNameAndState.put("countName","order_actual_total");
        countNameAndState.put("order","desc");

        Map<String ,Object> countNameAndYearAndState = new HashMap<>();
        countNameAndYearAndState.put("order_time","2019");
        countNameAndYearAndState.put("order_state",1);
        countNameAndYearAndState.put("countName","order_actual_total");
        countNameAndYearAndState.put("order","desc");

        Map<String ,Object> countNameAndyearMonthAndState = new HashMap<>();
        countNameAndyearMonthAndState.put("order_time","2019-10");
        countNameAndyearMonthAndState.put("order_state",1);
        countNameAndyearMonthAndState.put("countName","order_actual_total");
        countNameAndyearMonthAndState.put("order","desc");

    }


    @Test
    public void testNum(){
        OrderBaseStatisitcVO vo1 = new OrderBaseStatisitcVO();
        //vo1.setYear(2019);
        //vo1.setMonth(10);
        vo1.setEmployeeId((long) 1);
        vo1.setOrderState(0);

        OrderBaseStatisitcVO vo = OrderBaseStatisitcVO.getProperties(request,vo1);

        //vo.setMonth(10);
        System.out.println("长度！"+statisticsMapper.statisticalOrderBaseTotal(vo).size());

    }

    @Test
    public void testTimeArea(){
        OrderBaseStatisitcVO vo1 = new OrderBaseStatisitcVO();
        vo1.setBeginDate("2018-1-1");
        vo1.setEndDate("2019-12-31");
        vo1.setDateCompany("%Y");
        OrderBaseStatisitcVO vo = OrderBaseStatisitcVO.getProperties(request,vo1);

        statisticsMapper.statisticalOrderBaseTotalByDateArea(vo);
    }

    @Test
    public void orderTest(){

        System.out.println(orderBaseExtMapper.selectOrderBaseInfo().get(0).getReturnDetailCount());

    }

}
