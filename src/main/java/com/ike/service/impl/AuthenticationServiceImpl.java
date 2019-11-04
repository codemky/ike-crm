package com.ike.service.impl;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.Permission;
import com.ike.pojo.Role;
import com.ike.pojo.User;
import com.ike.pojo.vo.RoleVo;
import com.ike.service.AuthenticationService;
import com.ike.service.RolePermissionService;
import com.ike.service.RoleService;
import com.ike.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;


    private Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public Result<Object> login(String username, String password, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            User user = (User) subject.getPrincipals().getPrimaryPrincipal();
            session.setAttribute("id", user.getId());  //自增id
            session.setAttribute("username",user.getUsername());  //用户名
            List<Role> roles = roleService.listRolesByUid(user.getId()); //角色集合
            List<String> roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
            logger.info("\n# # # # # 用户: " + username + ",请求登陆,角色为: " + roleNames + ",请求结果: success# # # # # ");
            return Result.success("登陆成功!");
        } catch (UnknownAccountException e) {
            logger.info("\n# # # # # 用户: " + username + ",请求登陆," + "请求结果: ERROR,用户名不存在# # # # # ");
            return Result.error(CodeMsg.LOGIN_ACCOUNT_ERROR);
        } catch (IncorrectCredentialsException e) {
            logger.info("\n# # # # # 用户: " + username + ",请求登陆," + "请求结果: ERROR,密码错误,错误密码为:  " + password + "  # # # # # ");
            return Result.error(CodeMsg.LOGIN_PASSWORD_ERROR);
        }catch (LockedAccountException e){
            logger.info("\n# # # # # 用户: " + username + ",请求登陆," + "请求结果: ERROR,账号已被冻结# # # # # ");
            return Result.error(CodeMsg.LOGIN_ACCOUNT_LOCK);
        }catch (Exception e) {
            logger.info("\n# # # # # 用户: " + username + ",请求登陆," + "请求结果: ERROR,未知错误 # # # # # ");
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Override
    public Result<Object> logout(HttpServletRequest request) {
        try {
            SecurityUtils.getSubject().logout();    // shiro 退出
            request.getSession().invalidate();      // session清空
            logger.info("\n# # # # # 退出系统 # # # # # ");
            return Result.success("退出成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @Override
    public Result<Object> updatePassword(HttpServletRequest request, String oldPwd, String newPwd) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");
        String username = (String) session.getAttribute("username");
        //从session拿到用户id然后从数据库查询
        User dbUser = userService.findById(id);
        String dbPassword = dbUser.getPassword();
        String dbSalt = dbUser.getSalt();
        Md5Hash md5Hash = new Md5Hash(oldPwd, dbSalt, 1);

        //用户输入的旧密码与数据库匹配,根据用户Id修改密码、盐值
        if ((md5Hash.toString()).equals(dbPassword)) {
            User user = new User();
            user.setId(id);
            String uuid = UUID.randomUUID().toString().substring(0, 8);
            Md5Hash hash = new Md5Hash(newPwd, uuid, 1);
            user.setPassword(hash.toString());
            user.setSalt(uuid);
            userService.updatePassword(user);
            logger.info("\n# # # # # 用户: "+username+" 修改密码成功 # # # # # ");
            return Result.success("修改密码成功!");
        } else {
            //用户输入的旧密码与数据库不匹配
            return Result.error(CodeMsg.LOGIN_PASSWORD_ERROR);
        }
    }

    @Override
    public Result<Object> getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");
        User user = userService.findById(id);
        if (null == user) {
            return Result.error(CodeMsg.ERROR);
        }

        Map<String, Object> map = new HashMap<>();
        List<Long> perIds = new ArrayList<>();  //权限id集合
        List<String> perNames = new ArrayList<>(); //权限Name集合
        List<Role> roles = roleService.listRolesByUid(id);
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<RoleVo> roleVos = rolePermissionService.listByRid(roleIds);
        List<String> RoleNames = roleVos.stream().map(Role::getRoleName).collect(Collectors.toList());
        List<List<Permission>> permissions = roleVos.stream().map(RoleVo::getPermissions).collect(Collectors.toList());
        int fatherSize = permissions.size();

        for (int i = 0; i < fatherSize; i++) {
            int childSize = permissions.get(i).size();
            List<Permission> childList = permissions.get(i);
            for (int j = 0; j < childSize; j++) {
                perIds.add(childList.get(j).getId());
                perNames.add(childList.get(j).getPermissionName());
            }
        }
        List<Long> disPerIds = perIds.stream().distinct().collect(Collectors.toList());
        List<String> disPerNames = perNames.stream().distinct().collect(Collectors.toList());

        map.put("user", user);
        map.put("permissionIds", disPerIds);
        map.put("permissionNames", disPerNames);
        map.put("roles",RoleNames);

        return null == map ? Result.error(CodeMsg.ERROR):Result.success(map);
    }
}
