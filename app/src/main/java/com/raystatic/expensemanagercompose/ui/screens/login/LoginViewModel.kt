package com.raystatic.expensemanagercompose.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raystatic.expensemanagercompose.data.remote.requests.LoginRequestBody
import com.raystatic.expensemanagercompose.data.repositories.UserRepository
import com.raystatic.expensemanagercompose.util.Resource
import com.raystatic.expensemanagercompose.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _isLoginStateHandled = SingleLiveEvent<Boolean>()
    val isLoginStateHandled: SingleLiveEvent<Boolean> get() = _isLoginStateHandled

    private val _isLoginSuccessful = SingleLiveEvent<Resource<Boolean>>()
    val isLoginSuccessful: SingleLiveEvent<Resource<Boolean>> get() = _isLoginSuccessful

    init {
        _isLoginStateHandled.value = false
    }

    fun auth(loginRequestBody: LoginRequestBody) = viewModelScope.launch {
        userRepository.authUser(loginRequestBody).collect {
            _isLoginSuccessful.value = it
        }
    }

    fun setIsLoginStateHandled(b:Boolean){
        _isLoginStateHandled.value = b
    }



}