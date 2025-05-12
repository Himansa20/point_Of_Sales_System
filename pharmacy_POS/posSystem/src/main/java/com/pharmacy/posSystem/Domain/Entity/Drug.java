package com.pharmacy.posSystem.Domain.Entity;

import com.pharmacy.posSystem.Domain.Enum.DrugType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private DrugType type;
    private Integer quantity;
    private double unitPrice;

}