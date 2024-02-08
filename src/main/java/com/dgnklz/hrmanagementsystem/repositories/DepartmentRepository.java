package com.dgnklz.hrmanagementsystem.repositories;

import com.dgnklz.hrmanagementsystem.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    boolean existsByDepartmentName(String departmentName);

    String findDepartmentByDepartmentName(String departmentName);

    Department findByDepartmentName(String name);
}
