package com.geekinstitut.smartmoney.controller;

import com.geekinstitut.smartmoney.dto.TransactionRequestDTO;
import com.geekinstitut.smartmoney.dto.TransactionResponseDTO;
import com.geekinstitut.smartmoney.model.Income;
import com.geekinstitut.smartmoney.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    /**
     * Récupérer toutes les transactions de type revenu.
     */
    @GetMapping
    public List<TransactionResponseDTO> getIncomes() {
        return incomeService.getAllIncomes();
    }

    /**
     * Récupérer une transaction de type revenu par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable UUID id) {
        return incomeService.getIncomeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Créer une transaction de type revenu.
     */
    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createIncome(@RequestBody TransactionRequestDTO requestDTO) {
        return ResponseEntity.ok(incomeService.createIncome(requestDTO));
    }

    /**
     * Mettre à jour une transaction  existante de type revenu.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable UUID id, @RequestBody Income updatedIncome) {
        return ResponseEntity.ok(incomeService.updateIncome(id, updatedIncome));
    }

    /**
     * Supprimer un income par ID.
     */
    public ResponseEntity<Void> deleteIncome(@PathVariable UUID id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
