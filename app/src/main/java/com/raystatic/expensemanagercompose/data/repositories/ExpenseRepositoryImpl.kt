package com.raystatic.expensemanagercompose.data.repositories

import android.util.Log
import com.raystatic.expensemanagercompose.data.local.dao.ExpenseDao
import com.raystatic.expensemanagercompose.data.remote.ApiService
import com.raystatic.expensemanagercompose.data.remote.dto.*
import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.domain.models.MonthlyExpenseItem
import com.raystatic.expensemanagercompose.domain.repositories.ExpenseRepository
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.PrefManager
import com.raystatic.expensemanagercompose.util.Resource
import com.raystatic.expensemanagercompose.util.Utility
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val expenseDao: ExpenseDao,
    private val prefManager: PrefManager
): ExpenseRepository{

    override fun deleteExpenseById(expenseId: Int): Flow<Resource<DeleteExpenseResponse>> = flow{
        try {
            emit(Resource.loading(null))
            val token = prefManager.getString(Constants.USERTOKENKEY) ?: ""
            val response = apiService.deleteExpenseById(token, expenseId)
            if (!response.error){
                expenseDao.deleteExpenseById(expenseId)
            }
            emit(Resource.success(response))
        }catch (e:HttpException){
            e.printStackTrace()
            emit(Resource.error(Constants.SOMETHING_WENT_WRONG,null))
        }catch (e: IOException){
            e.printStackTrace()
            emit(Resource.error(Constants.CHECK_INTERNET_ERROR,null))
        }
    }

    override fun getExpensesByMonth(month: String): Flow<Resource<List<Expense>?>> = flow {
        try {
            emit(Resource.loading(null))
            expenseDao.getAllExpensesFlow()
                .collect {
                    Log.d("TAGDEBUG", "expenseByMonth: ${it.size}")
                    val currentExpenses = it
                    val monthWiseList = currentExpenses.groupBy {
                        Utility.getMonthFromDate(it.date ?: it.updatedAt)
                    }
                    emit(Resource.success(monthWiseList.getOrDefault(month, listOf())))
                }
        }catch (e:Exception){
            e.printStackTrace()
            emit(Resource.error("An error occurred", null))
        }
    }

    override suspend fun getExpenseByIdFromCache(id:Int): Expense {
        return expenseDao.getExpenseById(id)
    }

    override fun updateExpense(
        token: String,
        updateExpenseRequest: UpdateExpenseRequest
    ): Flow<Resource<Boolean>>  = flow{
        try {
            emit(Resource.loading(false))
            val response = apiService.updateExpense(token = token,updateExpenseRequest = updateExpenseRequest)
            if (!response.error){
                expenseDao.updateExpense(
                    title = updateExpenseRequest.title,
                    amount = updateExpenseRequest.amount,
                    date = updateExpenseRequest.date,
                    expenseId = updateExpenseRequest.expenseId
                )
                emit(Resource.success(true))
            }else{
                emit(Resource.error(response.message,false))
            }

        }catch (e:HttpException){
            e.printStackTrace()
            emit(Resource.error(Constants.SOMETHING_WENT_WRONG,false))
        }catch (e: IOException){
            e.printStackTrace()
            emit(Resource.error(Constants.CHECK_INTERNET_ERROR, false))
        }
    }

    override fun getExpensesFromCache(): Flow<List<Expense>> {
        return expenseDao.getAllExpensesFlow()
    }

    override fun getExpensesMonthly(): Flow<Resource<List<MonthlyExpenseItem>>> = flow {
        try {
            emit(Resource.loading(null))


            expenseDao.getAllExpensesFlow()
                .collect {
                    val monthlyItems = mutableSetOf<MonthlyExpenseItem>()
                    Log.d("TAGDEBUG", "getExpensesMonthly: ${it.size}")
                val currentExpenses = it
                val monthWiseList = currentExpenses.groupBy {
                    it.date?.let { d->
                        Utility.getMonthFromDate(d)
                    } ?: kotlin.run {
                        Utility.getMonthFromDate(it.updatedAt)
                    }
                }

                monthWiseList.forEach {
                    val month = it.key
                    val amount = it.value.sumOf { it.amount.toDouble() }.toFloat()
                    val endingDate = "${Utility.getDateFromDate(it.value.first().date ?: it.value.first().updatedAt)} ${it.key}"
                    val startingDate = "${Utility.getDateFromDate(it.value.last().date ?: it.value.last().updatedAt)} ${it.key}"
                    val duration = "$endingDate - $startingDate"

                    val monthlyExpenseItem = MonthlyExpenseItem(
                        month = month,
                        amount = amount,
                        duration = duration
                    )

                    monthlyItems.add(monthlyExpenseItem)
                }

                emit(Resource.success(monthlyItems.toList()))

            }


        }catch (e:Exception){
            e.printStackTrace()
            emit(Resource.error("An error occurred", null))
        }

    }

    override fun getExpenses(): Flow<Resource<Boolean>>  = flow{
        try {
            val token = prefManager.getString(Constants.USERTOKENKEY) ?: ""
            if (token.isNotEmpty()){
                val response = apiService.getExpenses(token = token)
                if (!response.error){
                    response.data?.let { list: List<ExpenseDTO> ->
                        expenseDao.deleteAllExpenses()
                        for ( i in list){
                            val expenseItem = i.toExpense()
                            expenseDao.insert(expenseItem)
                        }
                        emit(Resource.success(true))
                    }
                }else{
                    emit(Resource.error(response.message, false))
                }
            }else{
                emit(Resource.error(Constants.SOMETHING_WENT_WRONG, false))
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