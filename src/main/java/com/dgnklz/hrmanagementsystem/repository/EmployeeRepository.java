package com.dgnklz.hrmanagementsystem.repository;

import com.dgnklz.hrmanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsEmployeeByNameAndSurname(String name, String surname);
}
