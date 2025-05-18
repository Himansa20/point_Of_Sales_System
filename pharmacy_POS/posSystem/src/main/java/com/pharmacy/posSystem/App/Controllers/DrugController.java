package com.pharmacy.posSystem.App.Controllers;

import com.pharmacy.posSystem.Domain.Entity.Drug;
import com.pharmacy.posSystem.Domain.Service.DrugService;
import com.pharmacy.posSystem.External.Repository.DrugRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class DrugController {
    private DrugService drugService;

    @GetMapping("/searchdrugs")
    public ResponseEntity<Map<String, Object>> searchDrugs(@RequestParam String keyword) {
        try {
            List<Drug> drugs = drugService.searchDrugs(keyword);

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", drugs);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Error searching drugs: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }
    @GetMapping("/drugs")
    public ResponseEntity<Map<String, Object>> getAllDrugs() {
        try {
            List<Drug> drugs = drugService.getAllDrugs();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", drugs);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Error retrieving drugs: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping("/drugs/{id}")
    public ResponseEntity<Map<String, Object>> getDrugById(@PathVariable Integer id) {
        try {
            Drug drug = drugService.getDrugById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", drug);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", "error");
            error.put("message", "Drug not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    @PostMapping("/drugs")
    public ResponseEntity<Map<String, Object>> addDrug(@RequestBody Drug drug) {
        try {
            Drug savedDrug = drugService.addDrug(drug);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", savedDrug);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", "error");
            error.put("message", "Error saving drug: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PutMapping("/drugs/{id}")
    public ResponseEntity<Map<String, Object>> updateDrug(@PathVariable Integer id, @RequestBody Drug drugDetails) {
        try {
            Drug updatedDrug = drugService.updateDrug(id, drugDetails);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", updatedDrug);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", "error");
            error.put("message", "Drug not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    @DeleteMapping("/drugs/{id}")
    public ResponseEntity<Map<String, Object>> deleteDrug(@PathVariable Integer id) {
        try {
            drugService.deleteDrug(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Drug deleted successfully");
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", "error");
            error.put("message", "Drug not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

}