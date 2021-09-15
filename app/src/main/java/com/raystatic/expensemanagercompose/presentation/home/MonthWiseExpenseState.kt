package com.raystatic.expensemanagercompose.presentation.home

import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.presentation.ui.theme.LightPurple
import androidx.compose.ui.graphics.Color
import com.raystatic.expensemanagercompose.presentation.ui.theme.LightPurpleLightVariant
import com.raystatic.expensemanagercompose.presentation.ui.theme.White

data class MonthWiseExpenseState(
    val monthWiseExpense:List<Expense>?=null,
    val isLoading:Boolean = false,
    val error:String = "",
    val backgroundColor: Color?=null,
    val textColor:Color?=null,
    val variantColor: Color?=null
)
