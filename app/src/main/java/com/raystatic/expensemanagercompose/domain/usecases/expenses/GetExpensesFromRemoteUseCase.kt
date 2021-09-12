package com.raystatic.expensemanagercompose.domain.usecases.expenses

import com.raystatic.expensemanagercompose.domain.repositories.ExpenseRepository
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExpensesFromRemoteUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository
) {

    operator fun invoke(token:String):Flow<Resource<Boolean>>{
        return expenseRepository.getExpenses(token = token)
    }

}