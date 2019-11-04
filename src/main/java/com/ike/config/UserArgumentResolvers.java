package com.ike.config;

import com.ike.common.exception.IKEException;
import com.ike.pojo.User;
import com.ike.service.UserService;
import org.apache.catalina.session.StandardSession;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Optional;

/**
 * 设置controller层方法参数-用户User 信息解析
 * @author wuguanming
 */
@Service
public class UserArgumentResolvers implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        //判断参数类型，决定是否对参数进行处理
        return clazz == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = request.getSession();

        Object id1 = session.getAttribute("id");
        if (null == id1) {
            throw new UnauthenticatedException();
        }
        Long id = (Long) session.getAttribute("id");
        return userService.findById(id);

    }
}
