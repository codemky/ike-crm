package com.ike.common.result;

import com.ike.common.constans.CodeMsg;
import lombok.Getter;

/**
 * 结果封装
 */
@Getter
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;


    /**
     * 成功时调用
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 失败时调用
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }

    /**
     * 失败时调用
     */
    public static <T> Result<T> error() {
        return error(CodeMsg.ERROR);
    }

    /**
     * 失败时调用
     */
    public static <T> Result<T> error(Integer code, String msg) {
        return error(new CodeMsg(code, msg));
    }

    private Result(T data) {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if ( cm == null ) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }
}
