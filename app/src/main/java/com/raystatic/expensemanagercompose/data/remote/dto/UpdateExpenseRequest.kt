package com.raystatic.expensemanagercompose.data.remote.dto

import com.raystatic.expensemanagercompose.domain.models.Expense

data class UpdateExpenseRequest(
    val title:String,
    val amount:Float,
    val expenseId:Int,
    val date:String
)