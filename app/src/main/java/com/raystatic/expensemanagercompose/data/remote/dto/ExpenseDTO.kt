package com.raystatic.expensemanagercompose.data.remote.dto

import com.raystatic.expensemanagercompose.domain.models.Expense

data class ExpenseDTO(
    val amount: Float,
    val createdAt: String,
    val date:String?=null,
    val id: Int,
    val title: String,
    val updatedAt: String,
    val userId: Int
)

fun ExpenseDTO.toExpense():Expense{
    return Expense(
        id = id,
        createdAt = createdAt,
        amount = amount,
        title = title,
        updatedAt = updatedAt,
        date = date
    )
}
