package seclab.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Harlan1994
 * Date: 2017/9/16
 * Time: 17:51
 * Description:
 */
@Table(name = "tb_user", indexes = {
        @Index(name = "index_id", columnList = "id"),
        @Index(name = "index_username", columnList = "username")
})
@Data
@Entity
@NoArgsConstructor
public class User {

    /**
     * 用户唯一id
     */
    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 35)
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 6, max = 32)
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nickname", length = 32)
    private String nickname;

    /**
     * 真实姓名
     */
    @Column(name = "realName", length = 5)
    private String realName;

    /**
     * 性别
     */
    @Column(name = "gender")
    private boolean gender;

    /**
     * 头像，url的形式
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 届(比如 2017届，2016届)
     */
    @Column(name = "session")
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

    // 冻结false，正常true
    @Column(name = "state", nullable = false)
    private boolean state;

    /**
     * 选择的主题，默认黑色主题（1）
     *  0 ： 白色主题
     */
    @Column(name = "theme", length = 2)
    private int theme;
}