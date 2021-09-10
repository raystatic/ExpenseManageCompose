package com.raystatic.expensemanagercompose.presentation.add_expenses

import com.raystatic.expensemanagercompose.domain.models.Expense

data class AddExpenseState(
    val addedExpense:Expense?=null,
    val isLoading:Boolean = false,
    val error:String = ""
)
