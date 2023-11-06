package com.example.empmanagementsystem.controller;

import com.example.empmanagementsystem.model.request.DepartmentRequest;
import com.example.empmanagementsystem.model.request.EmployeeRequest;
import com.example.empmanagementsystem.model.response.ApiResponse;
import com.example.empmanagementsystem.service.DepartmentService;
import com.example.empmanagementsystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    private final EmployeeService employeeService;
    @PostMapping("/employees")
    public ResponseEntity<ApiResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        logger.info("Create employee API: {}", employeeRequest);
        return employeeService.create(employeeRequest);
    }
    @GetMapping("/employees")
    public ResponseEntity<ApiResponse> getEmployeeById(@RequestParam Long id){
        logger.info("Retrieve department API for departmentId: {}", id);
        return employeeService.getEmployeeDetailsById(id);
    }
    @PutMapping("/employees")
    public ResponseEntity<ApiResponse> updateEmployee(@RequestParam Long id,
                                                      @RequestBody EmployeeRequest employeeRequest){
        logger.info("Update employee API: {}", employeeRequest);
        return employeeService.updateEmployeeDetailsById(id, employeeRequest);
    }
    @DeleteMapping("/employees")
    public ResponseEntity<ApiResponse> deleteEmployee(@RequestParam Long id){
        logger.info("Delete Employee API for departmentId: {}", id);
        return employeeService.deleteEmployeeById(id);
    }
}
