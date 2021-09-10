package com.raystatic.expensemanagercompose.presentation.splash

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.raystatic.expensemanagercompose.R
import com.raystatic.expensemanagercompose.data.remote.dto.LoginRequestBody
import com.raystatic.expensemanagercompose.presentation.Screen
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.Status
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navController: NavController,
    vm: SplashViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_anim))

        val scaffoldState = rememberScaffoldState()

        val coroutineScope = rememberCoroutineScope()


        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.Center)

        )

        val authState = vm.userAuthState.value


        if (authState.success){
            navController.popBackStack()
            navController.navigate(Screen.HomeScreen.route)
        }

        if (authState.error.isNotBlank()){
            LaunchedEffect(scaffoldState.snackbarHostState) {
                scaffoldState.snackbarHostState.showSnackbar(authState.error)
            }
        }
        val user = vm.user.observeAsState().value


        var userObserverCounter by remember {
            mutableStateOf(0)
        }

        if (user == null && userObserverCounter < 10){
            Log.d("TAGDEBUG", "SplashScreen: $userObserverCounter |  $user")
            userObserverCounter++
        }else{

            user?.let {

                val loginRequestBody = LoginRequestBody(
                    name = it.name,
                    email = it.email,
                    avatar = it.avatar
                )

                vm.auth(loginRequestBody)

            } ?: kotlin.run {
                navController.popBackStack()
                navController.navigate(Screen.LoginScreen.route)

            }
        }


    }

}