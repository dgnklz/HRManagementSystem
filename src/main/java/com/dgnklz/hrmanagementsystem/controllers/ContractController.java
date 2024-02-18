package com.dgnklz.hrmanagementsystem.controllers;

import com.dgnklz.hrmanagementsystem.services.abstracts.ContractService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.UpdateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetContractByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.UpdateContractResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080/api/contract/add
// http://localhost:8080/api/contract/getAll
// http://localhost:8080/api/contract/get/{id}
// http://localhost:8080/api/contract/update/{id}
// http://localhost:8080/api/contract/deleteById/{id}

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/contract")
@AllArgsConstructor
public class ContractController {

    private ContractService service;

    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<CreateContractResponse> add(@Valid @RequestBody CreateContractRequest request){
        return service.add(request);

    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<List<GetAllContractsResponse>> getAll(){return service.getAll();}

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<GetContractByIdResponse> getById(@PathVariable int id){
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DataResult<UpdateContractResponse> update(@Valid @RequestBody UpdateContractRequest request, @PathVariable int id){
        return service.update(request, id);
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Result deleteById(@PathVariable int id) {return service.deleteById(id);}

}
