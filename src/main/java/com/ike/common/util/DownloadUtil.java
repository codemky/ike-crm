package com.ike.common.util;


import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 *
 * @Date：2019-10-17 14:41
 * @Description：<描述>
 */
public final class DownloadUtil {

    public static HttpServletResponse setResponseHeader(HttpServletResponse response , String fileName){
        String defaultFileName = "导出文件.xlsx";
        if (fileName != null){
            fileName = fileName + ".xlsx";
        }else {
            fileName = defaultFileName;
        }

        response.reset();
        //指定下载文件名
        try {
            response.addHeader("Content-Disposition","attachment;fileName=" +new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        return  response;
    }


}
