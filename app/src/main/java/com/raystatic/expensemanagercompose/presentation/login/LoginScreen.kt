package com.raystatic.expensemanagercompose.presentation.login

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.raystatic.expensemanagercompose.data.remote.dto.LoginRequestBody
import com.raystatic.expensemanagercompose.presentation.ui.theme.Black
import com.raystatic.expensemanagercompose.presentation.ui.theme.LightPurple
import com.raystatic.expensemanagercompose.presentation.ui.theme.White
import com.raystatic.expensemanagercompose.presentation.ui.theme.appFontFamily
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = hiltViewModel(),
    googleSignInClient: GoogleSignInClient,
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            var isLoadingVisible by remember {
                mutableStateOf(false)
            }

            val loginState = vm.loginAuthState.value


            if (loginState.success){
                navController.popBackStack()
                navController.navigate(Constants.HOME_SCREEN)
            }

            if (loginState.error.isNotBlank()){
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(loginState.error)
                }
            }

            if (loginState.isLoading){
                Dialog(
                    onDismissRequest = { isLoadingVisible = false },
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment= Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(White, shape = RoundedCornerShape(8.dp))
                    ) {
                        CircularProgressIndicator(
                            color = LightPurple
                        )
                    }
                }
            }

            Text(
                text = "Welcome!\nYou are just one tap away.",
                fontWeight = FontWeight.Medium,
                style = TextStyle(
                    color = Black,
                    textAlign = TextAlign.Start
                ),
                fontSize = 26.sp,
                fontFamily = appFontFamily,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == Activity.RESULT_OK){
                        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                        handleSignInResult(task, scaffoldState, scope, vm)
                    }
                }

                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 10.dp,
                    modifier = Modifier
                        .clickable {
                            val signInIntent = googleSignInClient.signInIntent

                            launcher.launch(signInIntent)

                        },
                ) {
                    Row(
                        modifier = Modifier
                            .background(color = LightPurple)
                            .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),
                    ) {
                        Text(
                            text = "Login with Google",
                            fontFamily = appFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            style = TextStyle(
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }

    }


}

private fun handleSignInResult(
    task: Task<GoogleSignInAccount>?,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    vm: LoginViewModel
) {
    try {

        task?.result?.let { account->

            val name = account.displayName ?: ""
            val email = account.email ?: ""
            val avatar = account.photoUrl?.toString()

            val loginRequestBody = LoginRequestBody(
                name = name,
                email = email,
                avatar = avatar
            )
            Log.d("TAGDEBUG", "LoginScreen: google success: $loginRequestBody")

            vm.auth(loginRequestBody)

        } ?: kotlin.run {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(Constants.SOMETHING_WENT_WRONG)
            }
            Log.d("TAGDEBUG", "LoginScreen: google error: null")

        }

    }catch (e: ApiException){
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(Constants.SOMETHING_WENT_WRONG)
        }

        Log.d("TAGDEBUG", "LoginScreen: google error: ${e.message}")

    }
}
