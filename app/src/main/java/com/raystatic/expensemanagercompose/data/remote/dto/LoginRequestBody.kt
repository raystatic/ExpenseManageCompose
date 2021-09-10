package com.raystatic.expensemanagercompose.data.remote.dto

data class LoginRequestBody(
    val avatar: String?=null,
    val email: String,
    val name: String
)