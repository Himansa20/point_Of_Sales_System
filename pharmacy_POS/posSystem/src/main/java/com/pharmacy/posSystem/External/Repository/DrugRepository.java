package com.pharmacy.posSystem.External.Repository;

import com.pharmacy.posSystem.Domain.Entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer> {
    @Query("SELECT d FROM Drug d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Drug> searchDrugsByName(@Param("keyword") String keyword);
}