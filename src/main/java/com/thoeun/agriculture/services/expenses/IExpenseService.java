package com.thoeun.agriculture.services.expenses;

import com.thoeun.agriculture.models.Expenses;

import java.util.List;

public interface IExpenseService {
    Expenses createExpense(Expenses expenses);
    Expenses getExpenseById(Long expenseId);
    List<Expenses> getAllExpenses();
    Expenses updateExpense(Long expenseId, Expenses expenses);
    void deleteExpense(Long expenseId);
}
