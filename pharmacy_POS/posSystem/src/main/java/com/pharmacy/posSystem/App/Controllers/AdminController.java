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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

public class AdminController {
    private AdminService adminService;
    private AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<AdminEntity> Register(@RequestBody AdminEntity admin) {
        adminService.registerAdmin(admin);
        return new ResponseEntity<>(admin, HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminEntity loginRequest) {
        Optional<AdminEntity> optionalAdmin = adminRepository.findByUsername(loginRequest.getUsername());

        if (optionalAdmin.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        AdminEntity admin = optionalAdmin.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

}