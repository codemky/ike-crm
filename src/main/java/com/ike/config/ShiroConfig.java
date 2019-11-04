package com.ike.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.ike.shiro.PasswordMatcher;
import com.ike.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean
            (@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * 常见过滤器:
         * 		anon:无需认证就可以访问
         * 		authc:必须认证才能访问
         * 		user:如果使用rememberMe的功能可以直接访问
         * 		perms:该资源必须授予资源权限才能访问
         * 		role:该资源必须得到角色权限才能访问  add delete find update read
         * 	GENERATED	crm:read
         * GENERATED	crm_follow:read
         * GENERATED	crm_plan:read
         * GENERATED	crm_task:read
         * GENERATED	crm_allcustomer:read
         * GENERATED	crm_mycustomer:read
         * GENERATED	crm_complaint:read
         * GENERATED	product:read
         * GENERATED	financial:read
         * GENERATED	financial_order:read
         * GENERATED	financial_payback:read
         * GENERATED	financial_plan:read
         * GENERATED	financial_refund:read
         * GENERATED	personal:read
         * GENERATED	personal_message:read
         * GENERATED	personal_info:read
         * GENERATED	personal_pwd:read
         * GENERATED	manage:read
         * GENERATED	manage_dept:read
         * GENERATED	manage_role:read
         * GENERATED	count:read
         * GENERATED	count_customer:read
         * GENERATED	count_order:read
         * GENERATED	count_product:read
         */
        /*Map<String, String> filterMap = new LinkedHashMap<String, String>();
        //放行页面
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterMap.put("/tologin","anon");*/
        //授权过滤器当前授权拦截后，shiro会自动跳转到未授权页面
        /**
         * url: /crm
         *  /crm/follow/crm/plan  /crm/task  /crm/allcustomer  /crm/mycustomer  /crm/complaint
         *
         *  url: /product
         *
         *  url: /financial
         *  /financial/order  /financial/payback  /financial/plan  /financial/refund
         *
         *  url:/personal
         *  /personal/message  /personal/info  /personal/pwd
         *
         *  url:/manage
         *  /manage/dept  /manage/role
         *
         *  url:/count
         *  /count/customer  /count/order  /count/product
         *
         */
        /*filterMap.put("/crm/**", "perms[]");
        filterMap.put("/product/**", "perms[]");
        filterMap.put("/financial/**", "perms[]");
        filterMap.put("/personal/**", "perms[]");
        filterMap.put("/manage/**", "perms[]");
        filterMap.put("/count/**", "perms[]");*/

        //拦截页面
        //filterMap.put("/*", "authc");
        //设置登陆页面
        //shiroFilterFactoryBean.setLoginUrl("/tologin");

        //设置未授权页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        return shiroFilterFactoryBean;
    }


    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        return new DefaultWebSecurityManager(userRealm);
    }

    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(passwordMatcher());
        return realm ;
    }

    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    //通过spring管理shiro生命周期
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    //配置shiro框架提供的切面类，用于创建代理对象
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    //开启shiro注解支持
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    //自定义密码比较器
    public PasswordMatcher passwordMatcher(){
        return new PasswordMatcher();
    }
}
