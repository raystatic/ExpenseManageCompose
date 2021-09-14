package com.raystatic.expensemanagercompose.presentation.add_expenses

data class UpdateExpenseState(
    val updated:Boolean = false,
    val isLoading:Boolean = false,
    val error:String = ""
)
