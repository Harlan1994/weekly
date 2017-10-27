package seclab.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seclab.domain.entity.User;

import java.util.Optional;

/**
 * User: Harlan1994
 * Date: 2017/9/19
 * Time: 12:17
 * Description:
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}