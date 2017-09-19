package com.seclab.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * User: Harlan1994
 * Date: 2017/9/16
 * Time: 17:51
 * Description: 管理员实体类
 */
@Entity
@Table(name = "tb_admin")
@Data
public class Admin {
    /**
     * 管理员id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}