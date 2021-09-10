package com.raystatic.expensemanagercompose.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    val selectedDuration = mutableStateOf("Daily")

    fun setSelectedDuration(duration:String){
        selectedDuration.value = duration
    }

}