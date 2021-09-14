package com.raystatic.expensemanagercompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.raystatic.expensemanagercompose.presentation.add_expenses.AddExpense
import com.raystatic.expensemanagercompose.presentation.home.HomeScreen
import com.raystatic.expensemanagercompose.presentation.login.LoginScreen
import com.raystatic.expensemanagercompose.presentation.ui.theme.ExpenseManagerComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import com.raystatic.expensemanagercompose.presentation.splash.SplashScreen
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.PrefManager


@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    @Inject
    lateinit var prefManager: PrefManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseManagerComposeTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme

                val scaffoldState = rememberScaffoldState()
                val coroutineScope = rememberCoroutineScope()

                Surface(color = MaterialTheme.colors.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ){

                        composable(
                            route = Screen.SplashScreen.route
                        ){
                            SplashScreen(
                                navController = navController
                            )
                        }

                        composable(
                            route = Screen.LoginScreen.route
                        ){
                            LoginScreen(
                                navController = navController,
                                googleSignInClient = googleSignInClient
                            )
                        }

                        composable(
                            route = Screen.HomeScreen.route
                        ){
                            HomeScreen(
                                navController = navController,
                                prefManager = prefManager
                            )
                        }

                        composable(
                            Screen.AddExpensesScreen.route + "/{expense_id}"
                        ){
                            AddExpense(
                                navController = navController,
                                prefManager = prefManager
                            )
                        }

                    }
                }
            }
        }
    }


}

