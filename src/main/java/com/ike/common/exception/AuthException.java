package com.ike.common.exception;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Shiro异常、邮件异常捕获
 */
@ControllerAdvice
public class AuthException {
    private Logger logger = LoggerFactory.getLogger(AuthException.class);

    @ExceptionHandler(value = UnauthorizedException.class)//权限不足异常
    @ResponseBody
    public Result AuthorizationErrorHandler(Exception e) throws IOException {
        logger.info("\n# # # # 抛出异常: - - - - -> UnauthorizedException 权限不足 # # # # ");
        return Result.error(CodeMsg.NO_PERMISSION);
    }

    @ExceptionHandler(value = UnauthenticatedException.class)//用户未认证异常
    @ResponseBody
    public Result AuthenticationErrorHandler(Exception e) throws IOException {
        logger.info("\n# # # # 抛出异常: - - - - -> AuthenticationException 用户未认证# # # # ");
        return Result.error(CodeMsg.NO_LOGIN);
    }
}
