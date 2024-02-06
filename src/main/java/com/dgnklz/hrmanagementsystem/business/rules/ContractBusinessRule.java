package com.dgnklz.hrmanagementsystem.business.rules;

import com.dgnklz.hrmanagementsystem.core.exception.BusinessException;
import com.dgnklz.hrmanagementsystem.repository.ContractRepository;
import com.dgnklz.hrmanagementsystem.repository.EmployeeRepository;
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

    public void checkIfEmployeeExistById(int employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new BusinessException("Employee is not exist by id: " + employeeId);
        }
    }
}
