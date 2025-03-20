package com.geekinstitut.smartmoney.service;

import com.geekinstitut.smartmoney.dto.TransactionRequestDTO;
import com.geekinstitut.smartmoney.dto.TransactionResponseDTO;
import com.geekinstitut.smartmoney.model.Category;
import com.geekinstitut.smartmoney.model.Expense;
import com.geekinstitut.smartmoney.repository.CategoryRepository;
import com.geekinstitut.smartmoney.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Recupere toutes les Expense
     */
    public List<TransactionResponseDTO> getAllExpenses() {

        return expenseRepository.findAll()
                .stream()
                .map(Expense::toResponse)
                .collect(Collectors.toList());
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
    public TransactionResponseDTO createExpense(TransactionRequestDTO requestDTO) {
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(()-> new EntityNotFoundException("Category not found"));

        Expense expense = new Expense();
        expense.setCategory(category);
        expense.setAmount(requestDTO.getAmount());
        expense.setNote(requestDTO.getNote());
        expense.setDate(requestDTO.getDate());

        return expenseRepository.save(expense).toResponse();
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
