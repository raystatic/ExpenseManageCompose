package com.raystatic.expensemanagercompose.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raystatic.expensemanagercompose.domain.usecases.user.GetUserUseCase
import com.raystatic.expensemanagercompose.util.Constants.defaultDurationList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    val user = getUserUseCase().asLiveData()

    private val _selectedDuration = mutableStateOf("Daily")
    val selectedDuration:State<String> get() = _selectedDuration

    fun setSelectedDuration(duration:DurationSelector){
        _selectedDuration.value = duration.title
    }

}