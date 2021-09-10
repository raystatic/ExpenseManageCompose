package com.raystatic.expensemanagercompose.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raystatic.expensemanagercompose.data.remote.dto.LoginRequestBody
import com.raystatic.expensemanagercompose.data.repositories.UserRepositoryImpl
import com.raystatic.expensemanagercompose.domain.models.User
import com.raystatic.expensemanagercompose.domain.usecases.user.AuthUserUseCase
import com.raystatic.expensemanagercompose.domain.usecases.user.GetUserUseCase
import com.raystatic.expensemanagercompose.presentation.login.LoginAuthState
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
class SplashViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val authUserUseCase: AuthUserUseCase
):ViewModel() {

    val user = getUserUseCase().asLiveData()

    private val _userAuthState = mutableStateOf(SplashAuthState())
    val userAuthState:State<SplashAuthState> get() = _userAuthState


    fun auth(loginRequestBody: LoginRequestBody) = viewModelScope.launch {
        authUserUseCase(loginRequestBody).onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _userAuthState.value = SplashAuthState(success = it.data == true)
                }
                Status.ERROR -> {
                    _userAuthState.value = SplashAuthState(error = it.message ?: Constants.UNKNOWN_ERROR)
                }
                Status.LOADING -> {
                    _userAuthState.value = SplashAuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}