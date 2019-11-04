package com.ike.common.util;

import java.util.ArrayList;

/**
 * @Date：2019-10-18 21:37
 * @Description：<描述>
 */
public final class DateUtil {

    /**
     * 获取一到十二月
     * @return
     */
    public static ArrayList<String> getMonth(){
        ArrayList<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++){
            monthList.add(getZero(i));
        }
        return monthList;
    }

    /**
     * 获取一个月的天数
     * @param year
     * @param month
     * @return
     */
    public static ArrayList<String> getDays(int year, int month){
        ArrayList<String> dayList = new ArrayList<>();
        int dayslength = 30;
        //判断一个月有几天
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dayslength = 31;
                break;
            //判断是否为闰年
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    dayslength = 29;
                } else {
                    dayslength = 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                dayslength = 30;
                break;
            default:
                dayslength = 30;
        }

        for (int i = 1; i <= dayslength; i++){
            dayList.add(getZero(i));
        }

        return dayList;
    }

    /**
     * 日期补零
     * @param num
     * @return
     */
    public static String getZero(int num){
        String numStr = String.valueOf(num);
        if (num < 10){
            numStr = "0" + numStr;
        }
        return numStr;
    }

}
