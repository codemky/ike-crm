package com.ike.controller;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.User;
import com.ike.service.AuthenticationService;
import com.ike.service.IMailService;
import com.ike.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

/**
 * 处理登陆、注册、注销、修改密码
 *  GET :       获得一个资源
 *  POST :      创建一个新的资源
 *  PUT ：     修改一个资源的状态
 *  DELETE :   删除一个资源
 */
@Api(value = "认证控制器", description = "用户账号模块")
@RestController
@RequestMapping("/Authen")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private IMailService mailService;


    @PostMapping("/login")
    @ApiOperation(value = "登陆接口(测试通过),测试账号: Kyre  123456", notes = "登陆接口")
    public Result<Object> login(@RequestParam("name") String name, @RequestParam("pwd") String pwd, HttpServletRequest request) {
        return authenticationService.login(name, pwd, request);
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    @ApiOperation(value = "注销接口(测试通过)", notes = "注销接口")
    public Result<Object> logout(HttpServletRequest request) {
        return authenticationService.logout(request);
    }

    @PutMapping("/updatePwd")
    @RequiresAuthentication
    @ApiOperation(value = "修改密码接口(测试通过)", notes = "修改密码接口")
    public Result<Object> updatePwd(HttpServletRequest request, String oldPwd, String newPwd) {
        return authenticationService.updatePassword(request, oldPwd, newPwd);
    }

    @GetMapping("/getUserInfo")
    @RequiresAuthentication
    @ApiOperation(value = "获取当前用户信息(测试通过)", notes = "获取当前用户信息")
    public Result<Object> getLoginUserInfo(HttpServletRequest request) {
        return authenticationService.getUserInfo(request);
    }

    @GetMapping("/getAuthCode")
    @ApiOperation(value = "为指定邮箱发送验证码(测试通过)", notes = "发送验证码")
    public Result<Object> sendAuthCode(@RequestParam String toEmail, HttpServletRequest request) {
        if (toEmail.startsWith("www.")) {
            toEmail = toEmail.substring(4);
        }

        Optional<User> optional = userService.findUserByEmail(toEmail);
        if (optional.isPresent()) {
            String emailSubject = "ike客户管理系统:找回密码";
            String authCode = UUID.randomUUID().toString().substring(0, 6);
            mailService.sendSimpleMail(toEmail, emailSubject, authCode);
            HttpSession session = request.getSession();
            session.setAttribute("authCode", authCode);
            session.setAttribute("email", toEmail);
            return Result.success(CodeMsg.SUCCESS);
        }

        return Result.success(CodeMsg.EMAIL_ERROR);
    }

    @GetMapping("/checkAuthCode")
    @ApiOperation(value = "验证用户输入验证码,不区分大小写(测试通过)", notes = "验证验证码")
    public Result<Object> checkAuthCode(@RequestParam String InputAuthCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String authCode = (String) session.getAttribute("authCode");
        if (null == authCode) {
            return Result.error(CodeMsg.ERROR);
        } else {
            if (authCode.equalsIgnoreCase(InputAuthCode)) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.AUTHCODE_NOT_EQUAL);
    }

    @PutMapping("/findPwd")
    @ApiOperation(value = "忘记密码接口(测试通过)", notes = "忘记密码接口")
    public Result<Object> findPwd(HttpServletRequest request, String newPwd) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if (null == email) {
            return Result.error(CodeMsg.ERROR);
        }

        Optional<User> optional = userService.findUserByEmail(email);
        if (optional.isPresent()) {
            User user = optional.get();
            String salt = UUID.randomUUID().toString().substring(0, 8);
            Md5Hash md5Hash = new Md5Hash(newPwd, salt, 1);
            user.setSalt(salt);
            user.setPassword(md5Hash.toString());
            boolean update = userService.updatePassword(user);
            return update == true ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
        }

        return Result.error(CodeMsg.ERROR);
    }
}
