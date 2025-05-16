package com.pharmacy.posSystem.Domain.Service;

import com.pharmacy.posSystem.Domain.Entity.Drug;
import com.pharmacy.posSystem.External.Repository.DrugRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service
@AllArgsConstructor
public class DrugService {
    private DrugRepository drugRepository;

    public List<Drug> searchDrugs(String keyword) {
        try {
            return drugRepository.searchDrugsByName(keyword.toLowerCase());
        } catch (Exception e) {
            throw new RuntimeException("Database error: " + e.getMessage());
        }
    }
}