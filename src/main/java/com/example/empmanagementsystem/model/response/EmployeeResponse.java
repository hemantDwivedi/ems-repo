package com.example.empmanagementsystem.model.response;

import com.example.empmanagementsystem.model.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private String firstName;
    private String lastName;
    private String email;
    private DepartmentResponse department;
}
