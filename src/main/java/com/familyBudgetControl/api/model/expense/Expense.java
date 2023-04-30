package com.familyBudgetControl.api.model.expense;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "expanses")
@Getter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal expanseValue;
    private LocalDate date;

    public Expense(@Valid ExpenseRequest request) {
        this.description = request.description();
        this.expanseValue = request.value();
        this.date = request.date();
    }

    public void update(@Valid UpdateExpense updateExpense) {
        this.description = updateExpense.description();
        this.expanseValue = updateExpense.value();
        this.date = updateExpense.date();
    }
}
