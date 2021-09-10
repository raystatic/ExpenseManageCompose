package com.raystatic.expensemanagercompose.presentation.home

import androidx.compose.ui.graphics.Color


data class ExpensesItem(
    val title:String,
    val percentage:Float,
    val fromDate:String,
    val amount:Float,
    val backgroundColor:Color,
    val variantColor: Color,
    val highlightColor: Color
)
