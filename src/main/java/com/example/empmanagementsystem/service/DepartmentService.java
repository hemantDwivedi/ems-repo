package com.example.empmanagementsystem.service;

import com.example.empmanagementsystem.model.request.DepartmentRequest;
import com.example.empmanagementsystem.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {
    ResponseEntity<ApiResponse> create(DepartmentRequest request);
    ResponseEntity<ApiResponse> getDepartmentById(String id);
    ResponseEntity<ApiResponse> getDepartmentByName(String departmentName);
    ResponseEntity<ApiResponse> getAllDepartmentDetails();
    ResponseEntity<ApiResponse> update(String id, DepartmentRequest request);
    ResponseEntity<ApiResponse> deleteDepartmentById(String id);
}
