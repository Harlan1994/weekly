package com.seclab.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Harlan1994
 * Date: 2017/9/16
 * Time: 17:51
 * Description:
 */
@Data
@Entity
@Table(name = "tb_user")
public class User {

    /**
     * 用户唯一id
     */
    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * 昵称
     */
    @Column(name = "nickname", length = 32, nullable = false)
    private String nickname;

    /**
     * 真实姓名
     */
    @Column(name = "realName", length = 5)
    private String realName;

    /**
     * 头像，url的形式
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 届(比如 2017届，2016届)
     */
    @Column(name = "session", nullable = false)
    private int session;

    /**
     * 注册时间(例如2017-09-01 09:09:09)
     */
    @Column(name = "regDate")
    private Date regDate;

    /**
     * 发表的周记
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @OrderBy(value = "publishTime DESC")
    private List<Weekly> weeklies = new ArrayList<>();

    // 1：正常
    // 2：冻结
    // 3：删除
    @Column(name = "state", nullable = false)
    private int state;
}