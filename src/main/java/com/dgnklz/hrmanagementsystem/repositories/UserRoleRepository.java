package com.dgnklz.hrmanagementsystem.repositories;

import com.dgnklz.hrmanagementsystem.models.securities.UserERole;
import com.dgnklz.hrmanagementsystem.models.securities.Userrole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Userrole, Integer> {
    Userrole findByName(UserERole name);
}