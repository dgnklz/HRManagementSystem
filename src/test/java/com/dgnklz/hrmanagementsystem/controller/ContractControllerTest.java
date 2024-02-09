package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.controllers.ContractController;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessDataResult;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessResult;
import com.dgnklz.hrmanagementsystem.services.abstracts.ContractService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.UpdateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetContractByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.UpdateContractResponse;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ContractControllerTest {

    @Mock
    private ContractService serviceMock;

    @InjectMocks
    private MocksFacilitator mocks;

    @InjectMocks
    private ContractController controller;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testAddContract() {
        CreateContractRequest request = mocks.createContractRequest();

        CreateContractResponse expectedResponse = mocks.createContractResponse();

        when(serviceMock.add(request)).thenReturn(new SuccessDataResult<>(expectedResponse, "Created Contract"));

        DataResult<CreateContractResponse> result = controller.add(request);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testGetAllContracts() {

        GetAllContractsResponse getAllContractsResponse = mocks.getAllContractsResponse();

        List<GetAllContractsResponse> expectedResponses = Arrays.asList(getAllContractsResponse);
        when(serviceMock.getAll()).thenReturn(new SuccessDataResult<>(expectedResponses, "All Contracts Listed"));

        DataResult<List<GetAllContractsResponse>> result = controller.getAll();

        assertEquals(expectedResponses, result.getData());
    }

    @Test
    void testUpdateContract() {
        int id = 1;
        UpdateContractRequest request = mocks.updateContractRequest();

        UpdateContractResponse expectedResponse = mocks.updateContractResponse();


        when(serviceMock.update(request, id)).thenReturn(new SuccessDataResult<>(expectedResponse, "Updated Successfully"));

        DataResult<UpdateContractResponse> result = controller.update(request, id);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testDeleteContractById() {
        int id = 1;

        Result expectedResult = new SuccessResult("Deleted successfully");
        when(serviceMock.deleteById(id)).thenReturn(expectedResult);

        Result result = controller.deleteById(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetContractById() {
        int id = 1;

        GetContractByIdResponse expectedResponse = mocks.getContractByIdResponse();

        when(serviceMock.getById(id)).thenReturn(new SuccessDataResult<>(expectedResponse, "Contract found by id"));

        DataResult<GetContractByIdResponse> result = controller.getById(id);

        assertEquals(expectedResponse, result.getData());
    }
}
