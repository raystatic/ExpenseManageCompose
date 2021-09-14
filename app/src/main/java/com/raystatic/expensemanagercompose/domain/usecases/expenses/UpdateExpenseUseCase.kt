package com.raystatic.expensemanagercompose.domain.usecases.expenses

import com.raystatic.expensemanagercompose.data.remote.dto.UpdateExpenseRequest
import com.raystatic.expensemanagercompose.domain.repositories.ExpenseRepository
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository
) {

    operator fun invoke(token:String, updateExpenseRequest: UpdateExpenseRequest):Flow<Resource<Boolean>>{
        return expenseRepository.updateExpense(token = token, updateExpenseRequest = updateExpenseRequest)
    }

}