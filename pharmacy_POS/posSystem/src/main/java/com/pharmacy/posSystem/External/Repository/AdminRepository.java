package com.pharmacy.posSystem.External.Repository;

import com.pharmacy.posSystem.Domain.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity ,Integer> {
    Optional<AdminEntity> findByUsername(String username);
}
