package com.dgnklz.hrmanagementsystem.business.conceretes;

import com.dgnklz.hrmanagementsystem.business.abstracts.ContractService;
import com.dgnklz.hrmanagementsystem.business.dto.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.contract.UpdateContractRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.contract.UpdateContractResponse;
import com.dgnklz.hrmanagementsystem.business.rules.ContractBusinessRule;
import com.dgnklz.hrmanagementsystem.core.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;
import com.dgnklz.hrmanagementsystem.core.result.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.core.result.SuccessResult;
import com.dgnklz.hrmanagementsystem.entity.Contract;
import com.dgnklz.hrmanagementsystem.repository.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractManager implements ContractService {
    private ContractRepository repository;
    private ModelMapperService mapper;
    private ContractBusinessRule rule;

    @Override
    public DataResult<CreateContractResponse> add(CreateContractRequest request) {
        Contract contract = mapper.forRequest().map(request, Contract.class);
        repository.save(contract);
        CreateContractResponse response = mapper.forResponse().map(contract, CreateContractResponse.class);
        return new SuccessDataResult<>(response, "Created Role");
    }

    @Override
    public DataResult<List<GetAllContractsResponse>> getAll() {
        List<Contract> contracts = repository.findAll();
        List<GetAllContractsResponse> responses = contracts
                .stream()
                .map(contract -> mapper.forResponse().map(contract, GetAllContractsResponse.class))
                .toList();
        return new SuccessDataResult<>(responses, "All Contracts Listed");
    }

    @Override
    public DataResult<UpdateContractResponse> update(UpdateContractRequest request, int id) {
        rule.checkIfContractNotExistById(id);
        rule.checkIfEmployeeExistById(request.getEmployeeId());
        Contract contract = mapper.forRequest().map(request, Contract.class);
        contract.setId(id);
        repository.save(contract);
        UpdateContractResponse response = mapper.forResponse().map(contract, UpdateContractResponse.class);
        return new SuccessDataResult<>(response, "Updated Successfully");
    }

    public Result deleteById(int id ){
        rule.checkIfContractNotExistById(id);
        repository.deleteById(id);
        return new SuccessResult("Deleted contract Successfully");
    }
}
