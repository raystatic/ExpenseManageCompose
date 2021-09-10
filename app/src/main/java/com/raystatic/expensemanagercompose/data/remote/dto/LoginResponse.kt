package com.raystatic.expensemanagercompose.data.remote.dto

import com.raystatic.expensemanagercompose.domain.models.User

data class LoginResponse(
    val `data`: User?=null,
    val error: Boolean,
    val message: String,
    val token: String?=null
)