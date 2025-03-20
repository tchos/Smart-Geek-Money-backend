package com.geekinstitut.smartmoney.model;

import com.geekinstitut.smartmoney.dto.TransactionResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Double amount;

    private String note;

    @Column(nullable = false)
    private LocalDate date;

    /**
     * Convertit une transaction en DTO de réponse.
     */
    public TransactionResponseDTO toResponse() {
        return new TransactionResponseDTO(
                this.category.getId(),
                this.category.getName(),
                this.amount,
                this.note,
                this.date
        );
    }
}


