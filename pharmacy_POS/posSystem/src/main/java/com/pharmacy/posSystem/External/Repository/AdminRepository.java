package com.pharmacy.posSystem.External.Repository;

import com.pharmacy.posSystem.Domain.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<AdminEntity ,Integer> {
    Optional<AdminEntity> findByUsername(String username);


}