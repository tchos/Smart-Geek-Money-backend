package com.geekinstitut.smartmoney.service;

import com.geekinstitut.smartmoney.model.Income;
import com.geekinstitut.smartmoney.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;

    /**
     * Recupere toutes les Income
     */
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
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
    public Income createIncome(Income income) {
        return incomeRepository.save(income);
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
    public void updateIncome(UUID id) {
        if(!incomeRepository.existsById(id)) {
            throw new EntityNotFoundException("Expense not found with id: " + id);
        }
        incomeRepository.deleteById(id);
    }
}
