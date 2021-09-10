package com.raystatic.expensemanagercompose.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raystatic.expensemanagercompose.data.remote.dto.LoginRequestBody
import com.raystatic.expensemanagercompose.data.repositories.UserRepositoryImpl
import com.raystatic.expensemanagercompose.domain.usecases.user.AuthUserUseCase
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.Resource
import com.raystatic.expensemanagercompose.util.SingleLiveEvent
import com.raystatic.expensemanagercompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUserUseCase: AuthUserUseCase
): ViewModel() {

    private val _loginAuthState = mutableStateOf(LoginAuthState())
    val loginAuthState:State<LoginAuthState> get() = _loginAuthState

    fun auth(loginRequestBody: LoginRequestBody){
        authUserUseCase(loginRequestBody).onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _loginAuthState.value = LoginAuthState(success = it.data == true)
                }
                Status.ERROR -> {
                    _loginAuthState.value = LoginAuthState(error = it.message ?: Constants.UNKNOWN_ERROR)
                }
                Status.LOADING -> {
                    _loginAuthState.value = LoginAuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}