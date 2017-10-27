package seclab.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Harlan1994
 * Date: 2017/9/16
 * Time: 17:44
 * Description: 周记实体类
 */
@Table(name = "tb_weekly", indexes = {
        @Index(name = "index_id", columnList = "id"),
        @Index(name = "index_title", columnList = "title")
})
@Data
@Entity
@NoArgsConstructor
public class Weekly {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 32)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "lastModifyTime", nullable = false)
    private Date lastModifyTime;

    @Column(name = "publishTime", nullable = false)
    private Date publishTime;

    // 1.正常 2.删除 3.只对自己可见（个人发布）
    @Column(name = "state", nullable = false)
    private int state;
}