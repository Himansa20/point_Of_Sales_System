package com.pharmacy.posSystem.Domain.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="EmployeeEntity")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName; //1
    private String lastName; //2
    private String nic; //3
    private String mobile; //4
    private String address; //5
    private LocalDate dob; //6
    private String username; //7
    private String password; //8
}
