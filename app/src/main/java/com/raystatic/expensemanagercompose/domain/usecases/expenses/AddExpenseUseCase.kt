package com.raystatic.expensemanagercompose.domain.usecases.expenses

import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.domain.repositories.ExpenseRepository
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository
) {

    operator fun invoke(addExpenseRequest: AddExpenseRequest, token:String): Flow<Resource<Expense>> {
        return expenseRepository.addExpense(addExpenseRequest = addExpenseRequest, token = token)
    }

}