package com.ike.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Date：2019-10-24 0:26
 * @Description：用于文件导出的判断工具类，需要依赖于Converter和RegexUtil
 */
public final class ImportTestUtil {

    /**
     * 测试字段是否为空
     * @param index 下标
     * @param param 测试字段
     * @param name  测试字段所属模块名称
     * @return 结果正确返回null，否则返回报错（格式为"第index行出错,出错原因:name为空"）
     */
    public static String testNullOrEmpty(int index, String param, String name){
        String resultMsg = null;
        if (Objects.isNull(param) || param.isEmpty()) {
            resultMsg = "第" + index + "行出错," + " 出错原因:"+name+"为空";
            return resultMsg;
        }
        return resultMsg;
    }

    /**
     * 测试数字是否符合整数格式
     * @param index 下标
     * @param param 测试字段
     * @param name  测试字段所属模块名称
     * @return 结果正确返回null，否则返回报错（格式为"第index行出错,出错原因:非法的name"）
     */
    public static String testNum(int index, String param, String name){
        String resultMsg = testNullOrEmpty(index, param, name);
        //若果参数不为空，再判断格式
        if (resultMsg == null){
            if (!RegexUtil.checkDigit(param)){
                resultMsg = "第" + index + "行出错," + " 出错原因:"+name+"不合法";
                return resultMsg;
            }
        }

        return resultMsg;
    }

    /**
     * 测试数字是否符合金额格式(两位以内小数)
     * @param index 下标
     * @param param 测试字段
     * @param name  测试字段所属模块名称
     * @return 结果正确返回null，否则返回报错（格式为"第index行出错,出错原因:非法的name"）
     */
    public static String testDecimals(int index, String param, String name){
        String resultMsg = testNullOrEmpty(index, param, name);
        //若果参数不为空，再判断格式
        if (resultMsg == null){
            if (!RegexUtil.checkDecimals(param)){
                resultMsg = "第" + index + "行出错," + " 出错原因:"+name+"不合法";
                return resultMsg;
            }
        }

        return resultMsg;
    }

    /**
     * 测试id是否符合格式
     * @param index 下标
     * @param param 测试字段
     * @param name  测试字段所属模块名称
     * @param ids 用于检测被测id是否存在此列表
     * @return 结果正确返回null，否则返回报错（格式为"第index行出错,出错原因:name不存在"）
     */
    public static String testId(int index, String param, String name, List<Long> ids){
        String resultMsg = testNum(index, param, name);
        //如果参数不为空且符合数字格式，再判断该id是否存在
        if (resultMsg == null){
            if (ids.indexOf(Converter.toLong(param)) == -1) {
                resultMsg = "第" + index + "行出错," + " 出错原因:"+name+"不存在";
                return resultMsg;
            }
        }


        return resultMsg;
    }

    /**
     * 测试日期是否可以转成LocalDateTime类型
     * @param index 下标
     * @param param 测试字段
     * @param name  测试字段所属模块名称
     * @return
     */
    public static String testLocalDateTime(int index, String param, String name){
        String resultMsg = testNullOrEmpty(index, param, name);
        //如果参数不为空，再判断是否可以转换为时间
        if (resultMsg == null){
            try {
                parseLocalDateTime(param);
            }catch (Exception e){
                e.printStackTrace();
                resultMsg = "第" + index + "行出错," + " 出错原因:"+name+"不合法";
                return resultMsg;
            }

        }

        return resultMsg;
    }

    /**
     * excel时间转换为日期字符串
     * @param use1904windowing
     * @param value
     * @return
     */
    public static String getPOIDate(boolean use1904windowing, double value) {
        int wholeDays = (int) Math.floor(value);
        int millisecondsInDay = (int) ((value - (double) wholeDays) * 8.64E7D + 0.5D);
        Calendar calendar = new GregorianCalendar();
        short startYear = 1900;
        byte dayAdjust = -1;
        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1;
        } else if (wholeDays < 61) {
            dayAdjust = 0;
        }
        calendar.set(startYear, 0, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, millisecondsInDay);
        if (calendar.get(Calendar.MILLISECOND) == 0) {
            calendar.clear(Calendar.MILLISECOND);
        }
        Date date = calendar.getTime();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return s.format(date);
    }

    /**
     * 将excel日期转换为LocalDateTime
     * @param dateStr excel直接解析后获得的日期字符串
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String dateStr){
        dateStr = getPOIDate(false,Converter.toDouble(dateStr));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateStr + " 00:00:00",dtf);
    }

    /**
     * 将日期格式String转换为LocalDateTime
     *
     * @return yyyy-MM-dd
     */
    public static LocalDateTime parseStringLocalDateTime(String dateStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateStr + " 00:00:00", dtf);
    }

}
