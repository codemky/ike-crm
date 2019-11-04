package com.ike.shiro;

import com.ike.pojo.Permission;
import com.ike.pojo.Role;
import com.ike.pojo.User;
import com.ike.pojo.vo.RoleVo;
import com.ike.service.RolePermissionService;
import com.ike.service.RoleService;
import com.ike.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePermissionService rolePermissionService;
	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		List<String> perNames = new ArrayList<>(); //权限Name集合
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		//获取当前登陆用户
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();

		//根据用户id获取角色id集合
		List<Role> roles = roleService.listRolesByUid(user.getId());
		List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());

		//根据角色id获取权限集合
		List<RoleVo> roleVos = rolePermissionService.listByRid(roleIds);
		List<List<Permission>> collect = roleVos.stream().map(RoleVo::getPermissions).collect(Collectors.toList());
		int fatherSize = collect.size();
		for (int i = 0; i < fatherSize; i++) {
			int childSize = collect.get(i).size();
			List<Permission> permissions = collect.get(i);
			for (int j = 0; j < childSize; j++) {
				perNames.add(permissions.get(j).getPermissionName());
			}
		}

		List<String> disPerNames = perNames.stream().distinct().collect(Collectors.toList());

		//添加资源的授权字符串
		info.addStringPermissions(disPerNames);    //添加权限列表，与shiro标签进行比较
		return info;
	}
	
	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		Optional<User> optional = userService.findUserByName(username);

		//账号存在
		if(optional.isPresent() ){
			User user = optional.get();
			//账号没被冻结
			if(user.getStatus() == 0){
				//SimpleAuthenticationInfo(Object principal, Object credentials, String realmName) {
				return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
			}else {
				throw new LockedAccountException();
			}
		}
		return null;
	}

}
