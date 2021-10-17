package com.raystatic.expensemanagercompose.domain.usecases.expenses

import com.raystatic.expensemanagercompose.data.remote.dto.DeleteExpenseResponse
import com.raystatic.expensemanagercompose.domain.repositories.ExpenseRepository
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteExpenseByIdUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository
) {

    operator fun invoke(expenseId:Int): Flow<Resource<DeleteExpenseResponse>> {
        return expenseRepository.deleteExpenseById(expenseId = expenseId)
    }


}