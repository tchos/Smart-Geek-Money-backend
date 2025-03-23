package com.geekinstitut.smartmoney.controller;

import com.geekinstitut.smartmoney.dto.TransactionRequestDTO;
import com.geekinstitut.smartmoney.dto.TransactionResponseDTO;
import com.geekinstitut.smartmoney.model.Expense;
import com.geekinstitut.smartmoney.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    /**
     * Récupérer toutes les transactions de type dépense.
     */
    @GetMapping
    public List<TransactionResponseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    /**
     * Récupérer une transaction de type dépense par ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable UUID id) {
        return expenseService.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Créer un nouvel expense.
     */
    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createExpense(@RequestBody TransactionRequestDTO requestDTO) {
        return ResponseEntity.ok(expenseService.createExpense(requestDTO));
    }

    /**
     * Mettre à jour un expense existante.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable UUID id, @RequestBody Expense updatedExpense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, updatedExpense));
    }

    /**
     * Supprimer un expense par ID.
     */
    public ResponseEntity<Void> deleteExpense(@PathVariable UUID id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
