package com.pharmacy.posSystem.App.Controllers;


import com.pharmacy.posSystem.App.dto.EmployeeLoginDTO;
import com.pharmacy.posSystem.Domain.Entity.AdminEntity;
import com.pharmacy.posSystem.Domain.Entity.EmployeeEntity;
import com.pharmacy.posSystem.Domain.Service.EmployeeService;
import com.pharmacy.posSystem.External.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class EmployeeControllers {
    private final PasswordEncoder passwordEncoder;
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    @PostMapping("/addemployee")

    public ResponseEntity<EmployeeEntity> registerEmployee(@RequestBody EmployeeEntity employeeEntity) {
        employeeService.registerEmployee(employeeEntity);
        return new ResponseEntity<>(employeeEntity, HttpStatus.CREATED);
    }
    //Employe loging
    @PostMapping("/loginemp")
    public ResponseEntity<?> employeelogin(@RequestBody EmployeeLoginDTO loginRequest) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findByUsername(loginRequest.getUsername());
        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        EmployeeEntity employee = optionalEmployee.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
        }
        // Generate a token (e.g., JWT) or return user details (without password)
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("username", employee.getUsername());
        return ResponseEntity.ok(response);
    }
}
