package com.thoeun.agriculture.repository;

import com.thoeun.agriculture.models.Expenses;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    boolean existsByExpenseType( String expenseType);
}

