package com.familyBudgetControl.api.model.revenue;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RevenueResponse(
        Long id,
        String description,
        BigDecimal value,
        LocalDate date
) {
    public RevenueResponse(Revenue revenue) {
        this(revenue.getId(), revenue.getDescription(), revenue.getRevenueValue(), revenue.getDate());
    }
}
