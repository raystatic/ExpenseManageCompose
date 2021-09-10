package com.raystatic.expensemanagercompose.presentation

sealed class Screen(val route:String){
    object SplashScreen:Screen("splash_screen")
    object LoginScreen:Screen("login_screen")
    object HomeScreen:Screen("home_screen")
    object AddExpensesScreen:Screen("add_expense_screen")
}
