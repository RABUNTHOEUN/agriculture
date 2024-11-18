package com.thoeun.agriculture.services.expenses;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Expenses;
import com.thoeun.agriculture.models.Farm;
import com.thoeun.agriculture.repository.ExpensesRepository;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService implements IExpenseService{

    private final ExpensesRepository expensesRepository;
    private final IFarmService farmService;

    @Override
    public Expenses createExpense(Expenses expenses) {
        Farm farm = farmService.getFarmById(expenses.getFarm().getFarmId());
        if (farm == null) {
            throw  new ResourceNotFoundException("Farm Not Found!");
        }
        if (expensesRepository.existsByExpenseType(expenses.getExpenseType())) {
            throw new AlreadyExistsException("Expenses with the same expense type already exists in this Expenses!");
        }
        return expensesRepository.save(expenses);
    }

    @Override
    public Expenses getExpenseById(Long expenseId) {
        return expensesRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expenses not found with ID: " + expenseId));
    }

    @Override
    public List<Expenses> getAllExpenses() {
        try {
            return expensesRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : ");
        }
    }

    @Override
    public Expenses updateExpense(Long expenseId, Expenses expenses) {
        Expenses updateExpenses = expensesRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expenses not found!"));
        updateExpenses.setExpenseType(expenses.getExpenseType());
        updateExpenses.setAmount(expenses.getAmount());
        updateExpenses.setExpenseDate(expenses.getExpenseDate());
        updateExpenses.setDescription(expenses.getDescription());
        return expensesRepository.save(updateExpenses);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        expensesRepository.findById(expenseId).ifPresentOrElse(expensesRepository::delete, () -> {
            throw new ResourceNotFoundException("Expenses Not Found!");
        });
    }
}
