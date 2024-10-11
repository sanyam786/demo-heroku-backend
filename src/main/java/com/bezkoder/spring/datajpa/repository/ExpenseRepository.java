package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {}
