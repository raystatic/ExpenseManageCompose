package com.raystatic.expensemanagercompose.data.remote.dto

data class GetExpensesResponse(
    val `data`: List<ExpenseDTO>?=null,
    val error: Boolean,
    val message: String
)