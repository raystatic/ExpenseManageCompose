package com.raystatic.expensemanagercompose.domain.repositories

import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    fun addExpense(addExpenseRequest: AddExpenseRequest, token:String): Flow<Resource<Expense>>

}