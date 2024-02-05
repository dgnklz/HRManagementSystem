package com.dgnklz.hrmanagementsystem.repository;

import com.dgnklz.hrmanagementsystem.entity.Contract;
import com.dgnklz.hrmanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Integer> {

    boolean existsContractById(int id);


}
