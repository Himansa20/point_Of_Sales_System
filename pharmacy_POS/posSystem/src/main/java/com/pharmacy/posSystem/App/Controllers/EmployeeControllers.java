package com.pharmacy.posSystem.App.Controllers;


import com.pharmacy.posSystem.App.dto.EmployeeDTO;
import com.pharmacy.posSystem.App.dto.EmployeeLoginDTO;

import com.pharmacy.posSystem.App.dto.Response.EmployeGeneral;
import com.pharmacy.posSystem.Domain.Entity.EmployeeEntity;
import com.pharmacy.posSystem.Domain.Exception.ResourceNotFoundException;
import com.pharmacy.posSystem.Domain.Service.EmployeeService;
import com.pharmacy.posSystem.External.Repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class EmployeeControllers {
    private final PasswordEncoder passwordEncoder;
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

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

    @GetMapping("/getemp")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/getone")
    public ResponseEntity<EmployeGeneral> getEmployeeById(@RequestParam Integer id) {
        return employeeService.getEmplooye(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeGeneral employeGeneral) {
        return employeeService.updateEmployee(employeGeneral);
    }

    @DeleteMapping("/delete")
        public ResponseEntity<String> deleteEmployee(@RequestParam Integer id) {
        return employeeService.deleteStudent(id);
        }
}
