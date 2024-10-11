package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Income;
import com.bezkoder.spring.datajpa.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }
}
