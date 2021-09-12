package com.raystatic.expensemanagercompose.presentation.home

data class ExpenseListState(
    val expensesUpdated:Boolean = false,
    val isLoading:Boolean = false,
    val error:String = ""
)
