package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {}
