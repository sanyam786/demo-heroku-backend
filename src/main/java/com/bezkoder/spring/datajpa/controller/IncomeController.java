package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Income;
import com.bezkoder.spring.datajpa.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "https://findash-25315.web.app")
@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @PostMapping
    public Income addIncome(@RequestBody Income income) {
        return incomeService.addIncome(income);
    }

    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
    }
}
