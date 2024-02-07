package com.dgnklz.hrmanagementsystem.controllers;

import com.dgnklz.hrmanagementsystem.services.abstracts.ContractService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.UpdateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.UpdateContractResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
@AllArgsConstructor
public class ContractController {

    private ContractService service;
    @PostMapping("/add")
    public DataResult<CreateContractResponse> add(@Valid @RequestBody CreateContractRequest request){
        return service.add(request);

    }

    @GetMapping("/getAll")
    public DataResult<List<GetAllContractsResponse>> getAll(){return service.getAll();}

    @PutMapping("/update/{id}")
    public DataResult<UpdateContractResponse> update(@Valid @RequestBody UpdateContractRequest request, @PathVariable int id){
        return service.update(request, id);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable int id) {return service.deleteById(id);}

}
