package com.ike.shiro;

import com.ike.pojo.User;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.MutablePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordMatcher extends SimpleCredentialsMatcher {

    private Logger logger = LoggerFactory.getLogger(PasswordMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        logger.info("\n# # # # # 调用密码对比器 # # # # # ");
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        String pwd = new String(utoken.getPassword());  //获取用户输入密码

        //Md5Hash()构造方法参数:  source(加密内容), salt(盐值), hashIterations(哈希次数)
        User principals = (User) info.getPrincipals().getPrimaryPrincipal();
        String salt = principals.getSalt();

        Md5Hash md5Hash = new Md5Hash(pwd, salt, 1); //默认加密一次
        String credentials = (String) info.getCredentials();  //从数据库获取加密后的密码

        return equals(md5Hash.toString(),credentials);
    }
}
