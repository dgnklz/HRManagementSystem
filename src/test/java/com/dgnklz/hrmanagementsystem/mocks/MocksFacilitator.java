package com.dgnklz.hrmanagementsystem.mocks;

import com.dgnklz.hrmanagementsystem.models.entities.*;
import com.dgnklz.hrmanagementsystem.models.securities.User;
import com.dgnklz.hrmanagementsystem.models.securities.UserDetailsImpl;
import com.dgnklz.hrmanagementsystem.models.securities.UserERole;
import com.dgnklz.hrmanagementsystem.models.securities.Userrole;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.CreateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.contract.UpdateContractRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.CreateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.department.UpdateDepartmentRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.CreateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.employee.UpdateEmployeeRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.CreateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.role.UpdateRoleRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SigninRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SignupRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.CreateContractResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetAllContractsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.GetContractByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.contract.UpdateContractResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.CreateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetAllDepartmentsResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.GetDepartmentByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.department.UpdateDepartmentResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.CreateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetAllEmployeesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetEmployeeByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.UpdateEmployeeResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.CreateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetAllRolesResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.GetRoleByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.role.UpdateRoleResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.security.SigninResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.security.SignupResponse;

import java.lang.reflect.Array;
import java.util.*;

public class MocksFacilitator {

    public Salary getSalary() {
        Salary salary = new Salary();
        salary.setSalaryDate(new Date());
        salary.setSalaryAmountMonth(5000);
        salary.setSalaryAmountYear(25000);

        return salary;
    }
//    public Address getAddress(){
//
//        Address address = new Address();
//        return address;
//    }

    public String getAddress(){

        return "address";
    }


    public CreateContractRequest createContractRequest() {
        CreateContractRequest request = new CreateContractRequest();

        request.setTypeContract("ContractType");
        request.setSalary(getSalary());
        request.setEndDateContract(new Date());
        request.setStartDateContract(new Date());
        request.setHoursAmountMonth(80);

        return request;
    }


    public CreateContractResponse createContractResponse() {
        CreateContractResponse expectedResponse = new CreateContractResponse();
        expectedResponse.setTypeContract("ContractType");
        expectedResponse.setSalary(getSalary());
        expectedResponse.setEndDateContract(new Date());
        expectedResponse.setStartDateContract(new Date());
        expectedResponse.setHoursAmountMonth(80);
        expectedResponse.setId(1);

        return expectedResponse;
    }


    public GetAllContractsResponse getAllContractsResponse() {

        GetAllContractsResponse getAllContractsResponse = new GetAllContractsResponse();
        getAllContractsResponse.setTypeContract("ContractType");
        getAllContractsResponse.setSalary(getSalary());
        getAllContractsResponse.setEndDateContract(new Date());
        getAllContractsResponse.setStartDateContract(new Date());
        getAllContractsResponse.setHoursAmountMonth(80);
        getAllContractsResponse.setId(1);

        return getAllContractsResponse;

    }

    public UpdateContractRequest updateContractRequest() {
        UpdateContractRequest request = new UpdateContractRequest();

        request.setTypeContract("ContractTypeUpdated");
        request.setSalary(getSalary());
        request.setEndDateContract(new Date());
        request.setStartDateContract(new Date());
        request.setHoursAmountMonth(80);
        request.setEmployeeId(1);

        return request;
    }

    public UpdateContractResponse updateContractResponse() {

        UpdateContractResponse expectedResponse = new UpdateContractResponse();
        expectedResponse.setTypeContract("ContractTypeUpdated");
        expectedResponse.setSalary(getSalary());
        expectedResponse.setEndDateContract(new Date());
        expectedResponse.setStartDateContract(new Date());
        expectedResponse.setHoursAmountMonth(80);
        expectedResponse.setEmployeeId(1);
        expectedResponse.setEmployeeId(1);

        return expectedResponse;
    }

    public GetContractByIdResponse getContractByIdResponse() {

        GetContractByIdResponse expectedResponse = new GetContractByIdResponse();
        expectedResponse.setTypeContract("ContractType");
        expectedResponse.setSalary(getSalary());
        expectedResponse.setEndDateContract(new Date());
        expectedResponse.setStartDateContract(new Date());
        expectedResponse.setHoursAmountMonth(80);

        return expectedResponse;
    }

    public CreateDepartmentRequest createDepartmentRequest(){
        CreateDepartmentRequest request = new CreateDepartmentRequest();
        request.setDepartmentName("Department Name");
        request.setDescription("Department Description");
        return request;
    }

    public CreateDepartmentResponse createDepartmentResponse(){
        CreateDepartmentResponse response = new CreateDepartmentResponse();
        response.setId(1);
        response.setDepartmentName("Department Name");
        response.setDescription("Department Description");

        return response;
    }
    public UpdateDepartmentRequest updateDepartmentRequest() {
        UpdateDepartmentRequest request = new UpdateDepartmentRequest();
        request.setDepartmentName("DepartmentName");
        request.setDescription("DepartmetnDescription");
        return request;
    }

    public UpdateDepartmentResponse updateDepartmentResponse(){
        UpdateDepartmentResponse expectedResponse = new UpdateDepartmentResponse();
        expectedResponse.setDepartmentName("DepartmentNameUpdated");
        expectedResponse.setDescription("DepartmentDescriptionUpdated");
        return  expectedResponse;
    }

    public GetAllDepartmentsResponse getAllDepartmentsResponse(){
        GetAllDepartmentsResponse getAllDepartmentsResponse = new GetAllDepartmentsResponse();

        getAllDepartmentsResponse.setDepartmentName("teste");
        getAllDepartmentsResponse.setId(1);
        getAllDepartmentsResponse.setDescription("teste");

        return getAllDepartmentsResponse;

    }

    public GetDepartmentByIdResponse getDepartmentByIdResponse(){
        GetDepartmentByIdResponse expectedResponse = new GetDepartmentByIdResponse();
        expectedResponse.setDepartmentName("Department Name");
        expectedResponse.setDescription("Department Description");

        return expectedResponse;
    }

    public CreateEmployeeRequest createEmployeeRequest() {
        CreateEmployeeRequest request = new CreateEmployeeRequest();

        request.setName("EmployeeName");
        request.setSurname("EmployeeSurname");
        request.setGender("MALE");
        request.setNationality("Nationality");
        request.setAddress(getAddress());
        request.setEmail("Email@Test.com");
        request.setDateOfBirth(new Date());
        request.setPhoneNumber("555");
        request.setRoleId(1);
        request.setDepartmentId(1);

        return request;
    }

    public CreateEmployeeResponse createEmployeeResponse(){
        CreateEmployeeResponse expectedResponse = new CreateEmployeeResponse();

        expectedResponse.setName("EmployeeName");
        expectedResponse.setSurname("EmployeeSurname");
        expectedResponse.setGender(Gender.MALE);
        expectedResponse.setNationality("Nationality");
        expectedResponse.setAddress(getAddress());
        expectedResponse.setEmail("Email@Test.com");
        expectedResponse.setDateOfBirth(new Date());
        expectedResponse.setPhoneNumber("555");

        return expectedResponse;
    }

    public GetAllEmployeesResponse getAllEmployeesResponse(){
        GetAllEmployeesResponse getAllEmployeesResponse = new GetAllEmployeesResponse();
        getAllEmployeesResponse.setName("EmployeeName");
        getAllEmployeesResponse.setSurname("EmployeeSurname");
        getAllEmployeesResponse.setGender(Gender.MALE);
        getAllEmployeesResponse.setNationality("Nationality");
        getAllEmployeesResponse.setAddress(getAddress());
        getAllEmployeesResponse.setEmail("Email@Test.com");
        getAllEmployeesResponse.setDateOfBirth(new Date());
        getAllEmployeesResponse.setPhoneNumber("555");
        getAllEmployeesResponse.setId(1);
        getAllEmployeesResponse.setRoleId(1);

        return getAllEmployeesResponse;
    }

    public UpdateEmployeeRequest updateEmployeeRequest(){
        UpdateEmployeeRequest request = new UpdateEmployeeRequest();

        request.setName("EmployeeName");
        request.setSurname("EmployeeSurname");
        request.setGender("MALE");
        request.setNationality("Nationality");
        request.setAddress(getAddress());
        request.setEmail("Email@Test.com");
        request.setDateOfBirth(new Date());
        request.setPhoneNumber("555");
        request.setRoleId(1);
        request.setDepartmentId(1);

        return request;
    }

    public UpdateEmployeeResponse updateEmployeeResponse(){
        UpdateEmployeeResponse expectedResponse = new UpdateEmployeeResponse();
        expectedResponse.setName("EmployeeName");
        expectedResponse.setSurname("EmployeeSurname");
        expectedResponse.setGender(Gender.MALE);
        expectedResponse.setNationality("Nationality");
        expectedResponse.setAddress(getAddress());
        expectedResponse.setEmail("Email@Test.com");
        expectedResponse.setDateOfBirth(new Date());
        expectedResponse.setPhoneNumber("555");
        expectedResponse.setRole(new Role());

        return expectedResponse;
    }

    public GetEmployeeByIdResponse getEmployeeByIdResponse() {
        GetEmployeeByIdResponse expectedResponse = new GetEmployeeByIdResponse();
        expectedResponse.setName("EmployeeName");
        expectedResponse.setSurname("EmployeeSurname");
        expectedResponse.setGender(Gender.MALE);
        expectedResponse.setNationality("Nationality");
        expectedResponse.setAddress(getAddress());
        expectedResponse.setEmail("Email@Test.com");
        expectedResponse.setDateOfBirth(new Date());
        expectedResponse.setPhoneNumber("555");
        expectedResponse.setRole(new Role());

        return expectedResponse;
    }

    public CreateRoleRequest createRoleRequest(){
        CreateRoleRequest request = new CreateRoleRequest();
        request.setBenefits("Test");
        request.setName("NameTest");
        request.setPosition("PositionTest");

        return request;
    }

    public CreateRoleResponse createRoleResponse(){
        CreateRoleResponse expectedResponse = new CreateRoleResponse();
        expectedResponse.setBenefits("Test");
        expectedResponse.setName("NameTest");
        expectedResponse.setPosition("PositionTest");

        return expectedResponse;
    }

    public GetAllRolesResponse getAllRolesResponse(){
        GetAllRolesResponse getAllRolesResponse = new GetAllRolesResponse();
        getAllRolesResponse.setBenefits("Benefits");
        getAllRolesResponse.setName("RoleName");
        getAllRolesResponse.setPosition("RolePosition");
        getAllRolesResponse.setId(1);

        return getAllRolesResponse;
    }

    public UpdateRoleRequest updateRoleRequest(){
        UpdateRoleRequest request = new UpdateRoleRequest();
        request.setBenefits("BenefitsUpdate");
        request.setName("NameUpdate");
        request.setPosition("PositionUpdate");
        return request;
    }

    public UpdateRoleResponse updateRoleResponse(){
        UpdateRoleResponse expectedResponse = new UpdateRoleResponse();
        expectedResponse.setBenefits("BenefitsUpdate");
        expectedResponse.setName("NameUpdate");
        expectedResponse.setPosition("PositionUpdate");

        return expectedResponse;
    }

    public GetRoleByIdResponse getRoleByIdResponse() {
        GetRoleByIdResponse expectedResponse = new GetRoleByIdResponse();
        expectedResponse.setBenefits("Test");
        expectedResponse.setName("NameTest");
        expectedResponse.setPosition("PositionTest");

        return expectedResponse;
    }

    public Contract getContract(){
        Contract contract = new Contract();
        contract.setId(1);
        contract.setSalary(getSalary());
        contract.setTypeContract("TypeOfContract");
        contract.setEndDateContract(new Date());
        contract.setStartDateContract(new Date());
        contract.setHoursAmountMonth(80);

        return contract;
    }

    public Employee getEmployee(){
        Employee employee = new Employee();
        employee.setAge(20);
        employee.setId(1);
        employee.setAddress(getAddress());
        employee.setGender(Gender.MALE);
        employee.setEmail("Employe@Gmail.com");
        employee.setNationality("Nationality");
        employee.setName("EmployeeName");
        employee.setSurname("EmployeSurname");
        employee.setContract(getContract());
        employee.setDateOfBirth(new Date());
        employee.setPhoneNumber("555");
        employee.setRole(new Role());
        employee.setDepartment(getDepartment());

        return employee;
    }


    public Role getRole(){
        Role role = new Role();
        List<Employee> employeeList = Arrays.asList(new Employee());
        role.setId(1);
        role.setEmployees(employeeList);
        role.setName("RoleName");
        role.setBenefits("RoleBenefits");
        role.setPosition("RolePosition");

        return role;
    }


    public Department getDepartment(){
        Department department = new Department();

        List<Employee> employees = Arrays.asList(new Employee());
        department.setId(1);
        department.setDepartmentName("DepartmentName");
        department.setDescription("DepartmentDescription");
        department.setEmployees(employees);

        return department;
    }

    public Userrole getUserRoleModerator(){
        Userrole moderatorRole = new Userrole();
        moderatorRole.setId(1);
        moderatorRole.setName(UserERole.ROLE_MODERATOR);

        return moderatorRole;
    }

    public Userrole getUserRoleAdmin(){
        Userrole moderatorRole = new Userrole();
        moderatorRole.setId(1);
        moderatorRole.setName(UserERole.ROLE_ADMIN);

        return moderatorRole;
    }


    public User getUser(){
        User user = new User();
        user.setPassword("123456");
        user.setUsername("username");
        user.setEmail("username@gmail.com");
        user.setFirstname("UserFirstName");
        user.setLastname("UserLastName");
        user.setId(1);

        return user;

    }

    public SignupRequest getSignupRequest(){
        SignupRequest request = new SignupRequest();
        request.setUsername("username");
        request.setPassword("123456");
        request.setFirstname("UserFirstName");
        request.setLastname("UserLastName");
        request.setEmail("user@gmail.com");
        request.setRole(new Set<String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        });

        return request;
    }

    public SignupResponse getSignupResponse(){
        SignupResponse response = new SignupResponse();
        response.setRoles(Arrays.asList("Roles"));
        response.setEmail("UserEmail");
        response.setFirstname("UserName");
        response.setLastname("LastName");

        return response;
    }


    public User getUserSecurity(){
        User user = new User();
        user.setRoles(new Set<Userrole>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Userrole> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Userrole userrole) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Userrole> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        });

        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("username@gmail.com");
        user.setLastname("lastname");
        user.setFirstname("firstname");

        return user;

    }

    public SigninRequest getSigninRequest(){
        SigninRequest signinRequest = new SigninRequest();
        signinRequest.setPassword("123456");
        signinRequest.setUsername("UserName");

        return signinRequest;
    }

    public SigninResponse getSigninResponse(){
        SigninResponse signinResponse = new SigninResponse();
        signinResponse.setEmail("user@gmail.com");
        signinResponse.setRoles(Arrays.asList("Roles"));
        signinResponse.setUsername("username");
        signinResponse.setToken("token");

        return signinResponse;
    }





}
