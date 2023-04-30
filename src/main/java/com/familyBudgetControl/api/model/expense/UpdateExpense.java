package com.familyBudgetControl.api.model.expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateExpense(
        @NotBlank
        String description,
        @NotNull
        @Positive
        BigDecimal value,
        @NotNull
        LocalDate date
) {
}
