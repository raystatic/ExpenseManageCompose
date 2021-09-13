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

    const val DEBUG_BASE_URL = "https://expensemanagercompose.herokuapp.com/api/"

    const val ExpenseManagerDB = "ExpenseManagerDB"

    const val KEY_PREF_NAME = "ExpenseManagerSharedPreferences"

    const val USERTOKENKEY = "user_token_key"
    const val USERNAMEKEY = "user_name_key"
    const val USEREMAILKEY = "user_email_key"
    const val USERAVATARKEY = "user_avatar_key"

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

}