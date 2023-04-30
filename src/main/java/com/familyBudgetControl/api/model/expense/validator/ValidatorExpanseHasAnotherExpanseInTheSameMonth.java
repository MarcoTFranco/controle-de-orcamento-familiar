package com.familyBudgetControl.api.model.expense.validator;

import com.familyBudgetControl.api.model.ValidationException;
import com.familyBudgetControl.api.model.expense.Expense;
import com.familyBudgetControl.api.model.expense.ExpenseRepository;
import com.familyBudgetControl.api.model.expense.ExpenseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ValidatorExpanseHasAnotherExpanseInTheSameMonth implements ValidatorExpense {

    @Autowired
    private ExpenseRepository repository;

    @Override
    public void validate(ExpenseRequest request) {
        var expenses = repository.findByDescription(request.description());
        if (!expenses.isEmpty()) {
            for (Expense expense: expenses) {
                var differenceInMonth = Duration.between(expense.getDate().atTime(1, 0, 0),
                        request.date().atTime(1, 0, 0)).toDays();
                if (differenceInMonth < 30) {
                    throw new ValidationException("There is already an expense registered with this name this month");
                }
            }

        }
    }
}
