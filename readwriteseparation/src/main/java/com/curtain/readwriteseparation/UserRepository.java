package com.curtain.readwriteseparation;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Curtain
 * @date 2019/4/23 9:36
 */

public interface UserRepository extends JpaRepository<User, Long> {
}
