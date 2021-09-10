package com.raystatic.expensemanagercompose.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey
    val id:Int,
    val title:String,
    val amount:Float,
    val updatedAt:String,
    val createdAt:String
)
