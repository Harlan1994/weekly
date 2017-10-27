package seclab.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * User: Harlan1994
 * Date: 2017/9/28
 * Time: 10:45
 * Description:
 */
@Table(name = "tb_admin")
@Data
@Entity
@NoArgsConstructor
public class Admin {

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
}