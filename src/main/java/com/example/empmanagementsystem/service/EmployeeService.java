package com.example.empmanagementsystem.service;

import com.example.empmanagementsystem.model.request.EmployeeRequest;
import com.example.empmanagementsystem.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    ResponseEntity<ApiResponse> create(EmployeeRequest employeeRequest);
    ResponseEntity<ApiResponse> getEmployeeDetailsById(Long id);
    ResponseEntity<ApiResponse> updateEmployeeDetailsById(Long id, EmployeeRequest employeeRequest);
    ResponseEntity<ApiResponse> deleteEmployeeById(Long id);
}
