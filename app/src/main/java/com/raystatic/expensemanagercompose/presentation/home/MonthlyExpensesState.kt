package com.raystatic.expensemanagercompose.presentation.home

import com.raystatic.expensemanagercompose.domain.models.MonthlyExpenseItem

data class MonthlyExpensesState(
    val monthlyExpense:List<MonthlyExpenseItem>? = null,
    val isLoading:Boolean  = false,
    val error:String = ""
)
