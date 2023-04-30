package com.familyBudgetControl.api.controller;

import com.familyBudgetControl.api.model.expense.*;
import com.familyBudgetControl.api.model.expense.validator.ValidatorExpense;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/despesas")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private List<ValidatorExpense> validators;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid ExpenseRequest request,
                                 UriComponentsBuilder componentsBuilder) {
        var expanse = new Expense(request);

        validators.forEach(v -> v.validate(request));

        expenseRepository.save(expanse);
        var uri = componentsBuilder.path("/depesas/{id}").buildAndExpand(expanse.getId()).toUri();
        return ResponseEntity.created(uri).body(new ExpenseResponse(expanse));
    }

    @GetMapping
    public ResponseEntity findAll() {
        var list = expenseRepository.findAll();
        return ResponseEntity.ok(list.stream().map(ExpenseResponse::new).toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        var expanse = expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(expanse);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var expanse = expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        expenseRepository.delete(expanse);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody @Valid UpdateExpense updateExpense) {
        var expanse = expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        expanse.update(updateExpense);
        return ResponseEntity.ok(new ExpenseResponse(expanse));
    }

}
