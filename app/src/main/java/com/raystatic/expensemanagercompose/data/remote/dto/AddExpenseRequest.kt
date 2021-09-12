package com.raystatic.expensemanagercompose.data.remote.dto

data class AddExpenseRequest(
    val title:String,
    val amount:Float,
    val date:String
)
