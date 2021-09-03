package com.raystatic.expensemanagercompose.data.remote.responses

import com.raystatic.expensemanagercompose.data.remote.models.User

data class LoginResponse(
    val `data`: User?=null,
    val error: Boolean,
    val message: String,
    val token: String?=null
)