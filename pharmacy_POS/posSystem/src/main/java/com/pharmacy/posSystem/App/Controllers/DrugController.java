package com.pharmacy.posSystem.App.Controllers;

import com.pharmacy.posSystem.Domain.Entity.Drug;
import com.pharmacy.posSystem.Domain.Service.DrugService;
import com.pharmacy.posSystem.External.Repository.DrugRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}