package seclab.domain.repository;

import seclab.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Harlan1994
 * Date: 2017/9/19
 * Time: 12:17
 * Description:
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}