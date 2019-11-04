package com.ike.pojo.vo;

import lombok.Data;

/**
 * @Author wgm
 * @date 2019/10/15 10:29
 * @Version 1.0
 */
@Data
public class CustomerStatisticVO {

    //年份
    private String year;

    //月份
    private String month;

    //日
    private String day;

    //总数
    private Integer count;
}
