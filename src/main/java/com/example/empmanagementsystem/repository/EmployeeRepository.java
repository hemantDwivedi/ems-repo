package com.example.empmanagementsystem.repository;

import com.example.empmanagementsystem.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
