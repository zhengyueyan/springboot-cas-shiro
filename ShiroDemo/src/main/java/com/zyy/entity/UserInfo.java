package com.zyy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 10:25 2018/6/7
 */
@Entity
@Data
@Table(name = "user_info")
public class UserInfo implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer uid;

    //帐号
    @Column(unique =true)
    private String userName;

    //名称（昵称或者真实姓名，不同系统不同定义）
    private String name;

    //密码;
    private String password;

    //加密密码的盐
    private String salt;

    //用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    private byte state;

    //立即从数据库中进行加载数据;
    // 一个用户具有多个角色
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roleList;

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.userName+this.salt;
    }

    @Override
    public String toString(){
        return this.userName;
    }

}
