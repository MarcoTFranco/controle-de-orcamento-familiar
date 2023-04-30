package com.familyBudgetControl.api.model.expense.validator;

import com.familyBudgetControl.api.model.expense.ExpenseRequest;

public interface ValidatorExpense {
    void validate(ExpenseRequest request);
}
