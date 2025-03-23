package com.geekinstitut.smartmoney.service;

import com.geekinstitut.smartmoney.dto.TransactionRequestDTO;
import com.geekinstitut.smartmoney.dto.TransactionResponseDTO;
import com.geekinstitut.smartmoney.model.Category;
import com.geekinstitut.smartmoney.model.Income;
import com.geekinstitut.smartmoney.repository.CategoryRepository;
import com.geekinstitut.smartmoney.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Recupere toutes les Income
     */
    public List<TransactionResponseDTO> getAllIncomes() {
        return incomeRepository.findAll()
                .stream()
                .map(Income::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Recuperer un Income par son ID
     */
    public Optional<Income> getIncomeById(UUID id) {
        return incomeRepository.findById(id);
    }

    /**
     * Crée un nouveau Income.
     */
    public TransactionResponseDTO createIncome(TransactionRequestDTO requestDTO) {
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Income income = new Income();
        income.setCategory(category);
        income.setAmount(requestDTO.getAmount());
        income.setNote(requestDTO.getNote());
        income.setDate(requestDTO.getDate());

        return incomeRepository.save(income).toResponse();
    }

    /**
     * Met à jour un Income existante.
     */
    public Income updateIncome(UUID id, Income updatedIncome) {
        return incomeRepository.findById(id).map
                (
                     existingIncome->
                     {
                         existingIncome.setCategory(updatedIncome.getCategory());
                         existingIncome.setAmount(updatedIncome.getAmount());
                         existingIncome.setNote(updatedIncome.getNote());
                         existingIncome.setDate(updatedIncome.getDate());
                         return incomeRepository.save(existingIncome);
                     }
                ).orElseThrow(()->new EntityNotFoundException("Expense not found with id: " + id));
    }

    /**
     * Supprime un Income par son ID.
     */
    public void deleteIncome(UUID id) {
        if(!incomeRepository.existsById(id)) {
            throw new EntityNotFoundException("Expense not found with id: " + id);
        }
        incomeRepository.deleteById(id);
    }
}
