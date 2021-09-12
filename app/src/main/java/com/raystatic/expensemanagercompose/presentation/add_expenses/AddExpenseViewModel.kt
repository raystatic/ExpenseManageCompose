package com.raystatic.expensemanagercompose.presentation.add_expenses

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.domain.usecases.expenses.AddExpenseUseCase
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.SingleLiveEvent
import com.raystatic.expensemanagercompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase
): ViewModel() {

    private val _isAddRequestHandled = mutableStateOf(false)
    val isAddRequestHandled: State<Boolean> get() = _isAddRequestHandled

    init {
        _isAddRequestHandled.value = false
    }

    fun setIsAddRequestHandled(b:Boolean){
        _isAddRequestHandled.value = b
    }

    private val _addExpenseState = mutableStateOf(AddExpenseState())
    val addExpenseState:State<AddExpenseState> get() = _addExpenseState

    fun addExpense(addExpenseRequest: AddExpenseRequest, token:String){
        addExpenseUseCase(addExpenseRequest, token = token).onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _addExpenseState.value = AddExpenseState(addedExpense = it.data)
                }

                Status.ERROR -> {
                    _addExpenseState.value = AddExpenseState(error = it.message ?: Constants.UNKNOWN_ERROR)
                }

                Status.LOADING -> {
                    _addExpenseState.value = AddExpenseState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _addExpenseRequest = SingleLiveEvent<AddExpenseRequest?>()
    val addExpenseRequest: SingleLiveEvent<AddExpenseRequest?> get() = _addExpenseRequest

    fun setAddExpenseRequest(addExpenseRequest: AddExpenseRequest){
        _addExpenseRequest.value = addExpenseRequest
    }

}