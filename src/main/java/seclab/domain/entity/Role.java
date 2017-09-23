package seclab.domain.entity;

import ch.qos.logback.classic.boolex.GEventEvaluator;
import lombok.Data;

import javax.persistence.*;

/**
 * User: Harlan1994
 * Date: 2017/9/22
 * Time: 21:25
 * Description:
 */
@Data
@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 32)
    private Long id;

    @Column(name = "roleName", nullable = false, unique = true)
    private String roleName;
}