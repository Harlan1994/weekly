package seclab.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * User: Harlan1994
 * Date: 2017/9/16
 * Time: 17:51
 * Description: 管理员实体类
 */
@Table(name = "tb_admin")
@Data
@Entity
public class Admin {
    /**
     * 管理员id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 32)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false)
    @Size(min = 8, max = 32)
    private String password;
}