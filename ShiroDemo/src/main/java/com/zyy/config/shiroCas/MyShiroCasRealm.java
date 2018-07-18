package com.zyy.config.shiroCas;

import com.zyy.Service.UserInfoService;
import com.zyy.entity.SysPermission;
import com.zyy.entity.SysRole;
import com.zyy.entity.UserInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @package: com.zyy.config
 * @description:
 * @author: zhengyueyan
 * @date: 16:52 2018/7/12
 */
public class MyShiroCasRealm extends CasRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroCasRealm.class);

    @Resource
    private UserInfoService userInfoService;

    @PostConstruct
    public void initProperty(){
        setCasServerUrlPrefix(ShiroCasConfiguration.casServerUrlPrefix);
        // 客户端回调地址
        setCasService(ShiroCasConfiguration.shiroServerUrlPrefix + ShiroCasConfiguration.casFilterUrlPattern);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String) principalCollection.getPrimaryPrincipal();

        //到数据库查是否有此对象(1.本地查询 2.可以远程查询casserver 3.可以由casserver带过来角色／权限其它信息)
        UserInfo user = userInfoService.findByUsername(loginName);
            if(user!=null){
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //给用户添加角色（让shiro去验证）
            for(SysRole role:user.getRoleList()){
                info.addRole(role.getRole());
                for(SysPermission p:role.getPermissions()){
                    info.addStringPermission(p.getPermission());
                }
            }
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }


}
