package com.raystatic.expensemanagercompose.data.remote

import com.raystatic.expensemanagercompose.data.remote.dto.*
import retrofit2.http.*

interface ApiService {


    @POST("user")
    suspend fun auth(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse

    @POST("expense")
    suspend fun addExpense(
        @Header("auth-token") token:String,
        @Body addExpenseRequest: AddExpenseRequest
    ): AddExpenseResponse

    @GET("expense")
    suspend fun getExpenses(
        @Header("auth-token") token: String
    ):GetExpensesResponse

    @PATCH("expense/updateExpense")
    suspend fun updateExpense(
        @Header("auth-token") token: String,
        @Body updateExpenseRequest: UpdateExpenseRequest
    ): UpdateExpenseResponse

}