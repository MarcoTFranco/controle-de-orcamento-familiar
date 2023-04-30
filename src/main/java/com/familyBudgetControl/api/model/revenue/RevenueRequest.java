package com.familyBudgetControl.api.model.revenue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RevenueRequest(
        @NotBlank
        String description,
        @Positive
        @NotNull
        BigDecimal value,
        @NotNull
        LocalDate date
) {
}
