package com.dgnklz.hrmanagementsystem.business.abstracts;

import com.dgnklz.hrmanagementsystem.business.dto.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.business.dto.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.business.dto.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.core.result.DataResult;

import java.util.List;

public interface ContractService {

    DataResult<CreateContractResponse> add(CreateContractRequest request);

    DataResult<List<GetAllContractsResponse>> getAll();
}
