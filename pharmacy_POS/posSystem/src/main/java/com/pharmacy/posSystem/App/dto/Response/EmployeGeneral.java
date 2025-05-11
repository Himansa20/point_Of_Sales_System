package com.pharmacy.posSystem.App.dto.Response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EmployeGeneral {
    private Integer emp_id;
    private String firstName;
    private String lastName;
    private String address;
    private String nic;
    private String mobile;
    private LocalDate dob;
    private String password;
}
