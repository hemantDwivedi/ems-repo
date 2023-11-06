package com.example.empmanagementsystem.repository;

import com.example.empmanagementsystem.model.entity.Department;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentRepositoryTest {
    @Autowired DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        departmentRepository.deleteAll();
    }

    @Test
    void testSave(){
        Department department = new Department();
        department.setDepartmentName("IT");

        Department savedDepartment = departmentRepository.save(department);

        Assertions.assertThat(savedDepartment).isNotNull();
        Assertions.assertThat(savedDepartment.getDepartmentId()).isNotNull();
    }

    @Test
    void testFindAll_departments(){
        Department department = new Department();
        department.setDepartmentName("IT");

        departmentRepository.save(department);

        assertEquals(1, departmentRepository.findAll().size());
    }
}