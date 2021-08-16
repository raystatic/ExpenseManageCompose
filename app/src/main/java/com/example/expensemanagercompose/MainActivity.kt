package com.example.expensemanagercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensemanagercompose.ui.screens.add_expenses.AddExpense
import com.example.expensemanagercompose.ui.screens.home.HomeScreen
import com.example.expensemanagercompose.ui.theme.ExpenseManagerComposeTheme
import com.example.expensemanagercompose.ui.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseManagerComposeTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Constants.HOME_SCREEN
                    ){
                        composable(
                            route = Constants.HOME_SCREEN
                        ){
                            HomeScreen(
                                navController = navController
                            )
                        }

                        composable(
                            Constants.ADD_EXPENSES_SCREEN
                        ){
                            AddExpense(
                                navController = navController
                            )
                        }

                    }
                }
            }
        }
    }
}

