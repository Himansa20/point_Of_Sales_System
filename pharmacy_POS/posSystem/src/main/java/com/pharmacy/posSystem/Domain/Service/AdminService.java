package com.pharmacy.posSystem.Domain.Service;

import com.pharmacy.posSystem.Domain.Entity.AdminEntity;
import com.pharmacy.posSystem.External.Repository.AdminRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<AdminEntity> registerAdmin(AdminEntity admin) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUsername(admin.getUsername());

        // Hash password
        adminEntity.setPassword(passwordEncoder.encode(admin.getPassword()));

        adminRepository.save(adminEntity);
        return new ResponseEntity<>(adminEntity, HttpStatus.OK);
    }
    public boolean authenticateAdmin(String username, String rawPassword) {
        return adminRepository.findByUsername(username)
                .map(admin -> passwordEncoder.matches(rawPassword, admin.getPassword()))
                .orElse(false);
    }
}
