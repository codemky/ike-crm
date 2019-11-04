package com.ike.common.handler;

import com.ike.common.result.Result;
import com.ike.common.constans.CodeMsg;
import com.ike.common.exception.IKEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理器
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result error(HttpMessageNotReadableException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error(CodeMsg.JSON_PARSE_ERROR);
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(IKEException.class)
    @ResponseBody
    public Result error(IKEException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }
}
