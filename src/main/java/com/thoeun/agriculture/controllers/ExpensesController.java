package com.thoeun.agriculture.controllers;

import com.thoeun.agriculture.exceptions.AlreadyExistsException;
import com.thoeun.agriculture.exceptions.ResourceNotFoundException;
import com.thoeun.agriculture.models.Expenses;
import com.thoeun.agriculture.response.ApiResponse;
import com.thoeun.agriculture.services.FarmService.IFarmService;
import com.thoeun.agriculture.services.expenses.IExpenseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/expenses")
@AllArgsConstructor
public class ExpensesController {

    private final IExpenseService expenseService;
    private final IFarmService farmService;

    @PostMapping
    public ResponseEntity<ApiResponse> createExpenses(@Valid @RequestBody Expenses expenses) {
        try {
            try {
                farmService.getFarmById(expenses.getFarm().getFarmId());
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            }
            Expenses createdExpenses = expenseService.createExpense(expenses);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Expenses created successfully", createdExpenses));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllLivestock () {
        try {
            List<Expenses> expenses = expenseService.getAllExpenses();
            return ResponseEntity.ok(new ApiResponse("Success", expenses));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/{expenseId}")
    public ResponseEntity<ApiResponse> getExpenseById(@PathVariable Long expenseId) {
        try {
            Expenses expenses = expenseService.getExpenseById(expenseId);
            return ResponseEntity.ok(new ApiResponse("Found ", expenses));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ApiResponse> updateExpense(@PathVariable Long expenseId,@Valid @RequestBody Expenses expenses) {

        try {
            Expenses updatedExpenses = expenseService.updateExpense(expenseId, expenses);
            return ResponseEntity.ok(new ApiResponse("Expenses update success", updatedExpenses));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<ApiResponse> deleteExpense(@PathVariable Long expenseId) {
        try {
            expenseService.deleteExpense(expenseId);
            return ResponseEntity.ok(new ApiResponse("Expenses deleted successfully.", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
