package com.dgnklz.hrmanagementsystem.repositories;

import com.dgnklz.hrmanagementsystem.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

   Role findRoleByName(String name);
    boolean existsRoleByName(String roleName);

}
