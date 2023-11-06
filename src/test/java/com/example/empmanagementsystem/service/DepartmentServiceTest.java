package com.example.empmanagementsystem.service;

import com.example.empmanagementsystem.exception.ResourceNotFoundException;
import com.example.empmanagementsystem.model.request.DepartmentRequest;
import com.example.empmanagementsystem.model.response.ApiResponse;
import com.example.empmanagementsystem.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired private DepartmentService departmentService;
    @Autowired private DepartmentRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void create() {
        DepartmentRequest request = new DepartmentRequest("IT");

        ResponseEntity<ApiResponse> response = departmentService.create(request);

        assertNotNull(response.getBody().getData());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getDepartmentById() {
        DepartmentRequest departmentRequest = new DepartmentRequest("IT");
        departmentService.create(departmentRequest);

        ResponseEntity<ApiResponse> response = departmentService.getDepartmentByName("IT");

        assertNotNull(response.getBody().getData());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThrows(ResourceNotFoundException.class, () -> departmentService.getDepartmentById("111212"));
    }

    @Test
    void getDepartmentByName() {
        DepartmentRequest departmentRequest = new DepartmentRequest("IT");
        departmentService.create(departmentRequest);

        ResponseEntity<ApiResponse> response = departmentService.getDepartmentByName("IT");

        assertEquals("IT", repository.findByDepartmentName("IT").get().getDepartmentName());
        assertNotNull(response.getBody().getData());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThrows(ResourceNotFoundException.class, () -> departmentService.getDepartmentByName("test"));
    }

    @Test
    void getAllDepartmentDetails() {
        DepartmentRequest departmentRequest = new DepartmentRequest("IT");
        departmentService.create(departmentRequest);

        assertNotNull(departmentService.getAllDepartmentDetails().getBody().getData());
    }

    @Test
    void update() {
        DepartmentRequest departmentRequest = new DepartmentRequest("IT");
        departmentService.create(departmentRequest);

        String id = repository.findByDepartmentName("IT").get().getDepartmentId();

        DepartmentRequest departmentRequest1 = new DepartmentRequest("SW");
        ResponseEntity<ApiResponse> updateResponse = departmentService.update(id, departmentRequest1);

        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertEquals("SW", repository.findByDepartmentName("SW").get().getDepartmentName());
    }

    @Test
    void deleteDepartmentById() {
        DepartmentRequest departmentRequest = new DepartmentRequest("IT");
        departmentService.create(departmentRequest);

        String id = repository.findByDepartmentName("IT").get().getDepartmentId();

        DepartmentRequest departmentRequest1 = new DepartmentRequest("SW");
        ResponseEntity<ApiResponse> updateResponse = departmentService.deleteDepartmentById(id);

        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertTrue(repository.findByDepartmentName("SW").isEmpty());
    }
}