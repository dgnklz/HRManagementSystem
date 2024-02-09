package com.dgnklz.hrmanagementsystem.repositories;

import com.dgnklz.hrmanagementsystem.models.securities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
