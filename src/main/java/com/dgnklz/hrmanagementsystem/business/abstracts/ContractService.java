package com.dgnklz.hrmanagementsystem.business.abstracts;

import com.dgnklz.hrmanagementsystem.business.dto.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.business.dto.requests.contract.UpdateContractRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.contract.UpdateContractResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;
import com.dgnklz.hrmanagementsystem.core.result.Result;

import java.util.List;

public interface ContractService {

    DataResult<CreateContractResponse> add(CreateContractRequest request);

    DataResult<List<GetAllContractsResponse>> getAll();

    DataResult<UpdateContractResponse> update(UpdateContractRequest request, int id);

    Result deleteById(int id );
}
