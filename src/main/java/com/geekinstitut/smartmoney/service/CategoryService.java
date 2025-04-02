package com.geekinstitut.smartmoney.service;

import com.geekinstitut.smartmoney.model.Category;
import com.geekinstitut.smartmoney.model.CategoryType;
import com.geekinstitut.smartmoney.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Recupere toutes les categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retourne la liste des categorie pour un type donne
     * @param type
     * @return
     */
    public List<Category> getCategoriesByType(CategoryType type) {
        return categoryRepository.findByType(type);
    }

    /**
     * Recuperer une categorie par son ID
     */
    public Optional<Category> getCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }

    /**
     * Crée une nouvelle catégorie.
     */
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Met à jour une catégorie existante: methode 1
     */
    public Category updateCategory(UUID id, Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedCategory.getName());
                    existingCategory.setPlannedInMonth(updatedCategory.getPlannedInMonth());
                    existingCategory.setType(updatedCategory.getType());
                    return categoryRepository.save(existingCategory);
                })
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    /**
     * Met à jour une catégorie existante: methode 2

    public Category updateCategory(UUID id, Category updatedCategory) {
        Category category = getCategoryById(id);
        category.setName(updatedCategory.getName());
        category.setPlannedInMonth(updatedCategory.getPlannedInMonth());
        category.setType(updatedCategory.getType());
        return categoryRepository.save(category);
    }
     */

    /**
     * Supprime une catégorie par son ID.
     */
    public void deleteCategory(UUID id) {
        if(!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
