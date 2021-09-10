package com.raystatic.expensemanagercompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.raystatic.expensemanagercompose.presentation.add_expenses.AddExpense
import com.raystatic.expensemanagercompose.presentation.home.HomeScreen
import com.raystatic.expensemanagercompose.presentation.login.LoginScreen
import com.raystatic.expensemanagercompose.presentation.ui.theme.ExpenseManagerComposeTheme
import com.raystatic.expensemanagercompose.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import com.raystatic.expensemanagercompose.presentation.splash.SplashScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient


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
                                navController = navController
                            )
                        }

                        composable(
                            Screen.AddExpensesScreen.route
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

