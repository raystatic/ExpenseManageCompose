package com.raystatic.expensemanagercompose.data.repositories

import com.raystatic.expensemanagercompose.data.local.dao.ExpenseDao
import com.raystatic.expensemanagercompose.data.remote.ApiService
import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.data.remote.dto.ExpenseDTO
import com.raystatic.expensemanagercompose.data.remote.dto.GetExpensesResponse
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

    override fun getExpensesFromCache(): Flow<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    override fun getExpenses(token: String): Flow<Resource<Boolean>>  = flow{
        try {
            val response = apiService.getExpenses(token = token)
            if (!response.error){
                response.data?.let { list: List<ExpenseDTO> ->
                    for ( i in list){
                        val expenseItem = i.toExpense()
                        expenseDao.insert(expenseItem)
                    }
                    emit(Resource.success(true))
                }
            }else{
                emit(Resource.error(response.message, false))
            }
        }catch (e:HttpException){
            e.printStackTrace()
            emit(Resource.error("${Constants.SOMETHING_WENT_WRONG} while syncing expenses",false))
        }catch (e:IOException){
            e.printStackTrace()
            emit(Resource.error("Cannot sync expenses. ${Constants.CHECK_INTERNET_ERROR}", false))
        }
    }

    override fun addExpense(addExpenseRequest: AddExpenseRequest, token:String): Flow<Resource<Expense>> = flow {
        try {
            emit(Resource.loading(null))
            val response = apiService.addExpense(addExpenseRequest = addExpenseRequest, token = token)
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
            emit(Resource.error(Constants.CHECK_INTERNET_ERROR, null))
        }
    }
}