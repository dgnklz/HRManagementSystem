package com.dgnklz.hrmanagementsystem.services.rules;

import com.dgnklz.hrmanagementsystem.cores.exceptions.types.BusinessException;
import com.dgnklz.hrmanagementsystem.repositories.ContractRepository;
import com.dgnklz.hrmanagementsystem.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContractBusinessRule {
    private ContractRepository contractRepository;
    private EmployeeRepository employeeRepository;

    public void checkIfContractNotExistById(int id){
        if(!contractRepository.existsContractById(id)){
            throw new BusinessException("Contract not exist");
        }
    }

    public void checkIfEmployeeNotExistById(int employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new BusinessException("Employee is not exist by id: " + employeeId);
        }
    }
}
