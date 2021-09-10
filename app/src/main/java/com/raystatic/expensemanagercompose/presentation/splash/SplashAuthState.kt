package com.raystatic.expensemanagercompose.presentation.splash

data class SplashAuthState(
    val success:Boolean = false,
    val isLoading:Boolean = false,
    val error:String = ""
)
