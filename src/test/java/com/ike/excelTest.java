package com.ike;

import com.alibaba.excel.ExcelReader;
import com.ike.common.util.ExcelListener;
import com.ike.pojo.OrderBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @Date：2019-10-21 22:47
 * @Description：<描述>
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class excelTest {

    @Test
    public void testExcelImport(){

        File file = new File("E:\\game\\test.xlsx");
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //实例化接口
        ExcelListener listener = new ExcelListener();
        ExcelReader reader = new ExcelReader(in, OrderBase.class, listener);

        //获取数据
        List<Object> list = listener.getDatas();
        System.out.println("获得了："+list.size()+"条数据！！！");

    }

}
