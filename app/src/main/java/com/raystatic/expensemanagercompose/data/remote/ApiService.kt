package com.raystatic.expensemanagercompose.data.remote

import com.raystatic.expensemanagercompose.data.remote.requests.LoginRequestBody
import com.raystatic.expensemanagercompose.data.remote.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @POST("user")
    suspend fun auth(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse

}