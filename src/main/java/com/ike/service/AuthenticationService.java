package com.ike.service;

import com.ike.common.result.Result;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    Result<Object> login(String username, String password, HttpServletRequest request);

    Result<Object> logout(HttpServletRequest request);

    Result<Object> updatePassword(HttpServletRequest request, String oldPwd, String newPwd);

    Result<Object> getUserInfo(HttpServletRequest request);
}
