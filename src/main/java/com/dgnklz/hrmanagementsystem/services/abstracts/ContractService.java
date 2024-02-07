package com.dgnklz.hrmanagementsystem.services.abstracts;

import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.UpdateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.UpdateContractResponse;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;

import java.util.List;

public interface ContractService {

    DataResult<CreateContractResponse> add(CreateContractRequest request);

    DataResult<List<GetAllContractsResponse>> getAll();

    DataResult<UpdateContractResponse> update(UpdateContractRequest request, int id);

    Result deleteById(int id );
}
