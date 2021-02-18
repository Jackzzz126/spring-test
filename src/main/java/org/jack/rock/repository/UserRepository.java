package org.jack.rock.repository;

import org.jack.rock.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserDao
 *
 * @author zhengzhe17
 * @date 2020/7/14
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
}
