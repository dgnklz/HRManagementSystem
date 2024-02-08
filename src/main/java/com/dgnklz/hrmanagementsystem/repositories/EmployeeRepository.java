package com.dgnklz.hrmanagementsystem.repositories;

import com.dgnklz.hrmanagementsystem.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsEmployeeByNameAndSurname(String name, String surname);

    Employee findEmployeeByEmail(String email);

    boolean existsEmployeeByEmail(String email);
}
