package com.pharmacy.posSystem.App.Controllers;

import com.pharmacy.posSystem.App.dto.AdminLoginDTO;
import com.pharmacy.posSystem.Domain.Entity.AdminEntity;
import com.pharmacy.posSystem.Domain.Service.AdminService;
import com.pharmacy.posSystem.External.Repository.AdminRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

public class AdminController {
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<AdminEntity> Register(@RequestBody AdminEntity admin) {
        adminService.registerAdmin(admin);
        return new ResponseEntity<>(admin, HttpStatus.OK);

    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginDTO loginDTO) {
        boolean isValid = adminService.authenticateAdmin(loginDTO.getUsername(), loginDTO.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
