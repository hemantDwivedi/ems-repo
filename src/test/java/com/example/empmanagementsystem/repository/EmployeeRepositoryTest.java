package com.example.empmanagementsystem.repository;

import com.example.empmanagementsystem.model.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {
    @Autowired private EmployeeRepository employeeRepository;

    @Test
    void testSave(){
        Employee employee = new Employee();
        employee.setFirstName("User");
        employee.setLastName("User");
        employee.setDepartmentId("12");
        employee.setEmail("user@email.com");
        Employee saved = employeeRepository.save(employee);

        assertNotNull(saved.getId());
    }

    @Test
    void testGetById(){
        Optional<Employee> employee = employeeRepository.findById(1L);

        assertTrue(true, String.valueOf(employee.isPresent()));
    }
}