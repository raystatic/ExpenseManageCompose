package com.raystatic.expensemanagercompose.domain.repositories

import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.data.remote.dto.GetExpensesResponse
import com.raystatic.expensemanagercompose.data.remote.dto.UpdateExpenseRequest
import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.domain.models.MonthlyExpenseItem
import com.raystatic.expensemanagercompose.presentation.home.ExpensesItem
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    fun addExpense(addExpenseRequest: AddExpenseRequest, token:String): Flow<Resource<Expense>>

    fun getExpenses():Flow<Resource<Boolean>>

    fun getExpensesFromCache():Flow<List<Expense>>

    fun getExpensesMonthly():Flow<Resource<List<MonthlyExpenseItem>>>

    fun updateExpense(token: String, updateExpenseRequest: UpdateExpenseRequest): Flow<Resource<Boolean>>

    suspend fun getExpenseByIdFromCache(id:Int):Expense

    fun getExpensesByMonth(month:String):Flow<Resource<List<Expense>?>>

}