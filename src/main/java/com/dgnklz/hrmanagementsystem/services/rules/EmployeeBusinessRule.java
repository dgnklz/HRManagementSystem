package com.dgnklz.hrmanagementsystem.services.rules;

import com.dgnklz.hrmanagementsystem.cores.exceptions.types.BusinessException;
import com.dgnklz.hrmanagementsystem.models.entities.Employee;
import com.dgnklz.hrmanagementsystem.models.entities.Gender;
import com.dgnklz.hrmanagementsystem.repositories.DepartmentRepository;
import com.dgnklz.hrmanagementsystem.repositories.EmployeeRepository;
import com.dgnklz.hrmanagementsystem.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@AllArgsConstructor
public class EmployeeBusinessRule {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private RoleRepository roleRepository;

    public void checkIfEmployeeNotExistById(int id){
        if(!employeeRepository.existsEmployeeById(id)){
            throw new BusinessException("Employee is not exist by this id: " + id);
        }
    }

    public void checkIfExistEmployee(String name, String surname) {
        if (employeeRepository.existsEmployeeByNameAndSurname(name, surname)){
            throw new BusinessException("Employee is exist");
        }
    }

    public Employee getEmployeeById(int id) {
        if (!employeeRepository.existsById(id)){
            throw new BusinessException("Employee is not exist");
        }
        return employeeRepository.findById(id).orElse(null);
    }

    public void checkIfEmployeeNotExistByEmail(String email){
        if(!employeeRepository.existsEmployeeByEmail(email)) {
            throw new BusinessException("Employee email not exist");
        }
    }

    public String checkIfEmployeeExistByEmail(String email, int id){
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (!email.equalsIgnoreCase(employee.getEmail())){
            if(employeeRepository.existsEmployeeByEmail(email)) {
                throw new BusinessException("Employee email not exist");
            }
        }else {
            return null;
        }
        return null;
    }

    public Gender getGender(String gender){
        switch (gender.toUpperCase()){
            case "MALE", "M" -> {return Gender.MALE;}
            case "FEMALE", "F" -> {return Gender.FEMALE;}
            default -> {return Gender.OTHER;}
        }
    }

    public void checkIfEmployeeTwentyYearsOld(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -20);
        Date currentDateMinusTwenty = calendar.getTime();
        if(!date.before(currentDateMinusTwenty)){
            throw new BusinessException("Employee should be min 20 years old");
        }
    }

    public int calculateAge(Date birthDate){
        Calendar dateOfBirth = Calendar.getInstance();
        Calendar currentTime = Calendar.getInstance();
        dateOfBirth.setTime(birthDate);
        currentTime.setTime(new Date());
        return (currentTime.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR));
    }

    public void checkIfDepartmentNotExistById(int id){
        if(!departmentRepository.existsById(id)){
            throw new BusinessException("Department not exist by id: " + id);
        }
    }

    public void checkIfRoleNotExistById(int id){
        if(!roleRepository.existsById(id)){
            throw new BusinessException("Role not exist by id: " + id);
        }
    }
}
