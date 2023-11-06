package com.example.empmanagementsystem.service.impl;

import com.example.empmanagementsystem.exception.ResourceNotFoundException;
import com.example.empmanagementsystem.model.entity.Department;
import com.example.empmanagementsystem.model.entity.Employee;
import com.example.empmanagementsystem.model.request.EmployeeRequest;
import com.example.empmanagementsystem.model.response.ApiResponse;
import com.example.empmanagementsystem.model.response.DepartmentResponse;
import com.example.empmanagementsystem.model.response.EmployeeResponse;
import com.example.empmanagementsystem.model.response.ResponseBuilder;
import com.example.empmanagementsystem.repository.DepartmentRepository;
import com.example.empmanagementsystem.repository.EmployeeRepository;
import com.example.empmanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ResponseBuilder responseBuilder;


    @Override
    public ResponseEntity<ApiResponse> create(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());


        if (departmentRepository.findById(employeeRequest.getDepartmentId()).isPresent()){
            employee.setDepartmentId(employee.getDepartmentId());
            employeeRepository.save(employee);
        } else {
                    throw new ResourceNotFoundException("Department not exist with id:" + employeeRequest.getDepartmentId());
        }
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "Employee details saved successfully", employee);
    }

    @Override
    public ResponseEntity<ApiResponse> getEmployeeDetailsById(Long id) {
        EmployeeResponse response = new EmployeeResponse();
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        Department department = departmentRepository.findById(employee.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id:" + id));
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentId(department.getDepartmentId());
        departmentResponse.setDepartmentName(department.getDepartmentName());
        response.setDepartment(departmentResponse);
        Map<String, Object> params = new HashMap<>();
        params.put("Id", id);
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "Employee details", response, params);
    }

    @Override
    public ResponseEntity<ApiResponse> updateEmployeeDetailsById(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        departmentRepository.findById(employeeRequest.getDepartmentId()).ifPresent(department -> employee.setDepartmentId(
                employeeRequest.getDepartmentId()
        ));
        employeeRepository.save(employee);
        Map<String, Object> params = new HashMap<>();
        params.put("Id", id);
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "Employee details", "Updated Successfully", params);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        employeeRepository.delete(employee);
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "Employee deleted successfully");
    }
}
