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
 * @Date: 10:56 2018/6/7
 */
@Entity
@Data
@Table(name = "sys_role")
public class SysRole implements Serializable{

    // 编号
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String role;

    // 角色描述,UI界面显示使用
    private String description;

    // 是否可用,如果不可用将不会添加给用户
    private Boolean available = Boolean.FALSE;

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;
    // 一个角色对应多个用户
    @ManyToMany
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<UserInfo> userInfos;

    @Override
    public String toString(){
        return this.role;
    }

}
