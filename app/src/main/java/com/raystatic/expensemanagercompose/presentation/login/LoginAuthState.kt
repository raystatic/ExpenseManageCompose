package com.raystatic.expensemanagercompose.presentation.login

data class LoginAuthState(
    val success:Boolean = false,
    val isLoading:Boolean = false,
    val error:String = ""
)
