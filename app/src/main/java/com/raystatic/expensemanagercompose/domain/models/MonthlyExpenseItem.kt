package com.raystatic.expensemanagercompose.domain.models

import com.raystatic.expensemanagercompose.presentation.home.ExpensesItem

data class MonthlyExpenseItem(
    val month:String,
    val duration:String,
    val amount:Float
)

fun MonthlyExpenseItem.toExpenseItem(): ExpensesItem {
    return ExpensesItem(
        title = this.month,
        amount = this.amount,
        fromDate = this.duration
    )
}
