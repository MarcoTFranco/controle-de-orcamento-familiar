package com.familyBudgetControl.api.model.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(
        String description,
        BigDecimal value,
        LocalDate date
) {
}
