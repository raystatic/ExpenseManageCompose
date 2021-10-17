package com.raystatic.expensemanagercompose.presentation.add_expenses

data class DeleteExpenseState(
    val expenseDeleted:Boolean = false,
    val isLoading:Boolean = false,
    val message:String = ""
)
