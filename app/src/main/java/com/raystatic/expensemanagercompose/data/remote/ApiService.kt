package com.raystatic.expensemanagercompose.data.remote

import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseResponse
import com.raystatic.expensemanagercompose.data.remote.dto.LoginRequestBody
import com.raystatic.expensemanagercompose.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {


    @POST("user")
    suspend fun auth(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse

    @POST("expense")
    suspend fun addExpense(
        @Body addExpenseRequest: AddExpenseRequest
    ): AddExpenseResponse

}