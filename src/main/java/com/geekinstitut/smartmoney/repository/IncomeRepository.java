package com.geekinstitut.smartmoney.repository;

import com.geekinstitut.smartmoney.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
