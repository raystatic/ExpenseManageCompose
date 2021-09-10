package com.raystatic.expensemanagercompose.data.repositories

import com.raystatic.expensemanagercompose.data.local.dao.ExpenseDao
import com.raystatic.expensemanagercompose.data.remote.ApiService
import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.data.remote.dto.toExpense
import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.domain.repositories.ExpenseRepository
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val expenseDao: ExpenseDao
): ExpenseRepository{

    override fun addExpense(addExpenseRequest: AddExpenseRequest): Flow<Resource<Expense>> = flow {
        try {
            emit(Resource.loading(null))
            val response = apiService.addExpense(addExpenseRequest = addExpenseRequest)
            if (!response.error){
                response.data?.let {expenseDTO ->
                    val expense = expenseDTO.toExpense()
                    expenseDao.insert(expense = expense)
                    emit(Resource.success(expense))
                }
            }else{
                emit(Resource.error(response.message, null))
            }
        }catch (e:HttpException){
            e.printStackTrace()
            emit(Resource.error(Constants.SOMETHING_WENT_WRONG,null))
        }catch (e:IOException){
            e.printStackTrace()
            emit(Resource.error(Constants.UNKNOWN_ERROR, null))
        }
    }
}