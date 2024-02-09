package com.dgnklz.hrmanagementsystem.services;

import com.dgnklz.hrmanagementsystem.cores.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.cores.results.DataResult;
import com.dgnklz.hrmanagementsystem.cores.results.Result;
import com.dgnklz.hrmanagementsystem.cores.results.SuccessResult;
import com.dgnklz.hrmanagementsystem.models.entities.Contract;
import com.dgnklz.hrmanagementsystem.repositories.ContractRepository;
import com.dgnklz.hrmanagementsystem.services.conceretes.ContractManager;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.UpdateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetContractByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.UpdateContractResponse;
import com.dgnklz.hrmanagementsystem.services.rules.ContractBusinessRule;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ContractManagerTest {

    @Mock
    private ContractRepository repositoryMock;

    @Mock
    private ModelMapperService mapperMock;

    @InjectMocks
    private MocksFacilitator mocks;

    @InjectMocks
    private ContractManager contractManager;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        ModelMapper modelMapperMock = mock(ModelMapper.class);

        when(mapperMock.forRequest()).thenReturn(modelMapperMock);
        when(mapperMock.forResponse()).thenReturn(modelMapperMock);

        ContractBusinessRule ruleMock = mock(ContractBusinessRule.class);

        contractManager = new ContractManager(repositoryMock, mapperMock, ruleMock);

    }

    @Test
    void testAddContract() {
        CreateContractRequest request = mocks.createContractRequest();
        Contract contract = mocks.getContract();
        CreateContractResponse expectedResponse = mocks.createContractResponse();

        when(mapperMock.forRequest().map(request, Contract.class)).thenReturn(contract);
        when(repositoryMock.save(contract)).thenReturn(contract);
        when(mapperMock.forResponse().map(contract, CreateContractResponse.class)).thenReturn(expectedResponse);

        DataResult<CreateContractResponse> result = contractManager.add(request);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testGetAllContracts() {
        List<Contract> contracts = new ArrayList<>();

        GetAllContractsResponse getAllContractsResponse = mocks.getAllContractsResponse();

        List<GetAllContractsResponse> expectedResponses = new ArrayList<>();

        when(repositoryMock.findAll()).thenReturn(contracts);
        when(mapperMock.forResponse().map(any(), eq(GetAllContractsResponse.class))).thenReturn(getAllContractsResponse);

        DataResult<List<GetAllContractsResponse>> result = contractManager.getAll();

        assertEquals(expectedResponses, result.getData());
    }

    @Test
    void testUpdateContract() {

        UpdateContractRequest request = mocks.updateContractRequest();
        int id = 123;
        Contract contract = mocks.getContract();
        UpdateContractResponse expectedResponse = mocks.updateContractResponse();

        when(mapperMock.forRequest().map(request, Contract.class)).thenReturn(contract);
        when(repositoryMock.save(contract)).thenReturn(contract);
        when(mapperMock.forResponse().map(contract, UpdateContractResponse.class)).thenReturn(expectedResponse);

        DataResult<UpdateContractResponse> result = contractManager.update(request, id);

        assertEquals(expectedResponse, result.getData());
    }

    @Test
    void testDeleteContractById() {
        int id = 123;

        Result result = contractManager.deleteById(id);

        verify(repositoryMock).deleteById(id);
        assertTrue(result instanceof SuccessResult);
    }

    @Test
    void testGetContractById() {
        int id = 123;
        Contract contract = mocks.getContract();
        GetContractByIdResponse expectedResponse = mocks.getContractByIdResponse();

        when(repositoryMock.findById(id)).thenReturn(Optional.of(contract));
        when(mapperMock.forResponse().map(contract, GetContractByIdResponse.class)).thenReturn(expectedResponse);

        DataResult<GetContractByIdResponse> result = contractManager.getById(id);

        verify(repositoryMock).findById(id);
        assertEquals(expectedResponse, result.getData());
    }


}