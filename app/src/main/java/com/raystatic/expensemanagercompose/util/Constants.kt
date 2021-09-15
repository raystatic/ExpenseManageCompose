package com.raystatic.expensemanagercompose.util

import com.raystatic.expensemanagercompose.presentation.home.DurationSelector

object Constants {

    const val HOME_SCREEN = "home_screen"
    const val ADD_EXPENSES_SCREEN = "add_expenses_screen"
    const val LOGIN_SCREEN = "login_screen"
    const val SPLASH_SCREEN = "splash_screen"

    const val SOMETHING_WENT_WRONG = "Something Went Wrong"
    const val CHECK_INTERNET_ERROR = "Couldn't reach server. Please check your internet connection"
    const val UNKNOWN_ERROR = "An unknown error occurred"

    const val ExpenseManagerDB = "ExpenseManagerDB"

    const val KEY_PREF_NAME = "ExpenseManagerSharedPreferences"

    const val USERTOKENKEY = "user_token_key"

    val defaultDurationList = listOf(
        DurationSelector(
            title = "Daily",
            selected = true
        ),
        DurationSelector(
            title = "Monthly",
            selected = false
        )
    )

    const val EXPENSE_ID = "expense_id"

}