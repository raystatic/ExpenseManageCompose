package com.raystatic.expensemanagercompose.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raystatic.expensemanagercompose.data.remote.dto.LoginRequestBody
import com.raystatic.expensemanagercompose.data.repositories.UserRepositoryImpl
import com.raystatic.expensemanagercompose.domain.usecases.user.AuthUserUseCase
import com.raystatic.expensemanagercompose.util.*
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

    private val _loginAuthState = MutableLiveData<Event<LoginAuthState>>()
    val loginAuthState:LiveData<Event<LoginAuthState>> get() = _loginAuthState

    fun auth(loginRequestBody: LoginRequestBody){
        authUserUseCase(loginRequestBody).onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _loginAuthState.value = Event(LoginAuthState(success = it.data == true))
                }
                Status.ERROR -> {
                    _loginAuthState.value = Event(LoginAuthState(error = it.message ?: Constants.UNKNOWN_ERROR))
                }
                Status.LOADING -> {
                    _loginAuthState.value = Event(LoginAuthState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }


}