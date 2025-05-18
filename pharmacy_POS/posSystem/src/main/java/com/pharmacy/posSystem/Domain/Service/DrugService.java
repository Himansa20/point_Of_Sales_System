package com.pharmacy.posSystem.Domain.Service;

import com.pharmacy.posSystem.Domain.Entity.Drug;
import com.pharmacy.posSystem.External.Repository.DrugRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }
    public Drug getDrugById(Integer id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Drug not found"));
    }
    public Drug addDrug(Drug drug) {
        return drugRepository.save(drug);
    }
    public Drug updateDrug(Integer id, Drug drugDetails) {
        Drug existingDrug = drugRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Drug not found"));

        existingDrug.setName(drugDetails.getName());
        existingDrug.setUnitPrice(drugDetails.getUnitPrice());
        existingDrug.setQuantity(drugDetails.getQuantity());
        // add any additional fields here

        return drugRepository.save(existingDrug);
    }
    public void deleteDrug(Integer id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Drug not found"));
        drugRepository.delete(drug);
    }

}