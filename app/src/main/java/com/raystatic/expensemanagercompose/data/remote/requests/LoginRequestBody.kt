package com.raystatic.expensemanagercompose.data.remote.requests

data class LoginRequestBody(
    val avatar: String?=null,
    val email: String,
    val name: String
)