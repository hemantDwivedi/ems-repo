package com.example.empmanagementsystem.service.impl;

import com.example.empmanagementsystem.exception.ResourceNotFoundException;
import com.example.empmanagementsystem.model.entity.Department;
import com.example.empmanagementsystem.model.request.DepartmentRequest;
import com.example.empmanagementsystem.model.response.ApiResponse;
import com.example.empmanagementsystem.model.response.ResponseBuilder;
import com.example.empmanagementsystem.repository.DepartmentRepository;
import com.example.empmanagementsystem.service.DepartmentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository repository;
    private ResponseBuilder responseBuilder;
    @Override
    public ResponseEntity<ApiResponse> create(DepartmentRequest request) {
        Department department = new Department();
        department.setDepartmentName(request.getDepartmentName());
        repository.save(department);
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "Department details saved successfully", department);
    }

    @Override
    public ResponseEntity<ApiResponse> getDepartmentById(String id) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id:" + id));

        Map<String, Object> params = new HashMap<>();
        params.put("Id", id);
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "Department details", department, params);
    }

    @Override
    public ResponseEntity<ApiResponse> getDepartmentByName(String departmentName) {
        Department department = repository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with departmentName:" + departmentName));
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "Department details", department);
    }

    @Override
    public ResponseEntity<ApiResponse> getAllDepartmentDetails() {
        List<Department> departments = repository.findAll();
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "All Department details", departments);
    }

    @Override
    public ResponseEntity<ApiResponse> update(String id, DepartmentRequest request) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id:" + id));
        department.setDepartmentName(request.getDepartmentName());
        Department updatedDepartment = repository.save(department);
        Map<String, Object> params = new HashMap<>();
        params.put("Id", id);
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "Update successfully", updatedDepartment, params);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteDepartmentById(String id) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id:" + id));
        repository.delete(department);
        Map<String, Object> params = new HashMap<>();
        params.put("Id", id);
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "Deleted successfully", params);
    }
}
