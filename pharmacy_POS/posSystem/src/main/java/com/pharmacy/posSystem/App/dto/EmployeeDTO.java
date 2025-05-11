package com.pharmacy.posSystem.App.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String nic;
    private String mobile;
    private String address;
    private LocalDate dob;
    private String username;
}
