package com.familyBudgetControl.api.model.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(
        Long id,
        String description,
        BigDecimal value,
        LocalDate date
) {
    public ExpenseResponse(Expense expense) {
        this(expense.getId(), expense.getDescription(), expense.getExpanseValue(), expense.getDate());
    }
}
