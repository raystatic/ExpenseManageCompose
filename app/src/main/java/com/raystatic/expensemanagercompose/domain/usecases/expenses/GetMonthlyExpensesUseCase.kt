package com.raystatic.expensemanagercompose.domain.usecases.expenses

import com.raystatic.expensemanagercompose.domain.models.MonthlyExpenseItem
import com.raystatic.expensemanagercompose.domain.repositories.ExpenseRepository
import com.raystatic.expensemanagercompose.presentation.home.ExpensesItem
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.math.exp

class GetMonthlyExpensesUseCase @Inject constructor(
    private val expensesRepository: ExpenseRepository
) {

    operator fun invoke():Flow<Resource<List<MonthlyExpenseItem>>>{
        return expensesRepository.getExpensesMonthly()
    }
}