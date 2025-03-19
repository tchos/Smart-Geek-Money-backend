package com.geekinstitut.smartmoney.repository;

import com.geekinstitut.smartmoney.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// JpaRepository<Category, UUID> Category = Nom entite et UUID = type de la clef primaire.

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}

