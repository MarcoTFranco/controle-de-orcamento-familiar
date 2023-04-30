package com.familyBudgetControl.api.model.revenue;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "revenues")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal revenueValue;
    private LocalDate date;

    public Revenue(@Valid RevenueRequest request) {
        this.description = request.description();
        this.revenueValue = request.value();
        this.date = request.date();
    }

    public void update(@Valid UpdateRevenue updateRevenue) {
        this.description = updateRevenue.description();
        this.revenueValue = updateRevenue.value();
        this.date = updateRevenue.date();
    }
}
