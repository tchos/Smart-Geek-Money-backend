package com.geekinstitut.smartmoney.service;

import com.geekinstitut.smartmoney.model.Expense;
import com.geekinstitut.smartmoney.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    /**
     * Recupere toutes les Expense
     */
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    /**
     * Recuperer un Expense par son ID
     */
    public Optional<Expense> getExpenseById(UUID id) {
        return expenseRepository.findById(id);
    }

    /**
     * Crée un nouveau Expense.
     */
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    /**
     * Met à jour un Expense existante.
     */
    public Expense updateExpense(UUID id, Expense updatedExpense) {
        return expenseRepository.findById(id).map
            (
                existingExpense->
                {
                    existingExpense.setCategory(updatedExpense.getCategory());
                    existingExpense.setAmount(updatedExpense.getAmount());
                    existingExpense.setNote(updatedExpense.getNote());
                    existingExpense.setDate(updatedExpense.getDate());
                    return expenseRepository.save(existingExpense);
                }
            ).orElseThrow(()->new EntityNotFoundException("Expense not found with id: " + id)
        );
    }

    /**
     * Supprime un Expense par son ID.
     */
    public void deleteExpense(UUID id) {
        if(!expenseRepository.existsById(id)) {
            throw new EntityNotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }
}
