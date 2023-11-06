package com.example.empmanagementsystem.controller;

import com.example.empmanagementsystem.model.request.DepartmentRequest;
import com.example.empmanagementsystem.model.response.ApiResponse;
import com.example.empmanagementsystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;
    @PostMapping("/departments")
    public ResponseEntity<ApiResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest){
        logger.info("Create department API: {}", departmentRequest);
        return departmentService.create(departmentRequest);
    }
    @GetMapping("/departments")
    public ResponseEntity<ApiResponse> getDepartmentById(@RequestParam String id){
        logger.info("Retrieve department API for departmentId: {}", id);
        return departmentService.getDepartmentById(id);
    }
    @GetMapping("/departments/name")
    public ResponseEntity<ApiResponse> getDepartmentByName(@RequestParam String name){
        logger.info("Retrieve department API for departmentName: {}", name);
        return departmentService.getDepartmentByName(name);
    }
    @GetMapping("/departments/list")
    public ResponseEntity<ApiResponse> getDepartments(){
        logger.info("Retrieve all department API");
        return departmentService.getAllDepartmentDetails();
    }
    @PutMapping("/departments")
    public ResponseEntity<ApiResponse> updateDepartment(@RequestParam String id, @RequestBody DepartmentRequest departmentRequest){
        logger.info("Update department API: {}", departmentRequest);
        return departmentService.update(id, departmentRequest);
    }
    @DeleteMapping("/departments")
    public ResponseEntity<ApiResponse> deleteDepartment(@RequestParam String id){
        logger.info("Delete department API for departmentId: {}", id);
        return departmentService.deleteDepartmentById(id);
    }
}
