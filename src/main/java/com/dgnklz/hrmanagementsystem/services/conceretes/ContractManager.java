package com.dgnklz.hrmanagementsystem.services.conceretes;

import com.dgnklz.hrmanagementsystem.services.abstracts.ContractService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.UpdateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetContractByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.UpdateContractResponse;
import com.dgnklz.hrmanagementsystem.services.rules.ContractBusinessRule;
import com.dgnklz.hrmanagementsystem.cores.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessResult;
import com.dgnklz.hrmanagementsystem.models.entities.Contract;
import com.dgnklz.hrmanagementsystem.repositories.ContractRepository;
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
    public DataResult<GetContractByIdResponse> getById(int id) {
        rule.checkIfContractNotExistById(id);
        Contract contract = repository.findById(id).orElse(null);
        GetContractByIdResponse response = mapper.forResponse().map(contract, GetContractByIdResponse.class);
        return new SuccessDataResult<>(response, "Contract found by id");
    }

    @Override
    public DataResult<UpdateContractResponse> update(UpdateContractRequest request, int id) {
        rule.checkIfContractNotExistById(id);
        rule.checkIfEmployeeNotExistById(request.getEmployeeId());
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
