package com.raystatic.expensemanagercompose.data.remote.dto

data class AddExpenseResponse(
    val `data`: ExpenseDTO?=null,
    val error: Boolean,
    val message: String
)