package com.raystatic.expensemanagercompose.presentation.add_expenses

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.data.remote.dto.UpdateExpenseRequest
import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.domain.usecases.expenses.AddExpenseUseCase
import com.raystatic.expensemanagercompose.domain.usecases.expenses.GetExpenseByIdFromCache
import com.raystatic.expensemanagercompose.domain.usecases.expenses.UpdateExpenseUseCase
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.Event
import com.raystatic.expensemanagercompose.util.SingleLiveEvent
import com.raystatic.expensemanagercompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase,
    private val updateExpenseUseCase: UpdateExpenseUseCase,
    private val getExpenseByIdFromCache: GetExpenseByIdFromCache,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _isAddRequestHandled = mutableStateOf(false)
    val isAddRequestHandled: State<Boolean> get() = _isAddRequestHandled

    private val _expenseByIdFromCache = SingleLiveEvent<Event<Expense>>()
    val expenseByIdFromCache:SingleLiveEvent<Event<Expense>> get() = _expenseByIdFromCache

    init {
        _isAddRequestHandled.value = false
        savedStateHandle.get<String>(Constants.EXPENSE_ID)?.let {
            if (it.toInt() != -1){
                getExpenseFromCache(id = it.toInt())
            }
        }
    }

    private fun getExpenseFromCache(id:Int) = viewModelScope.launch {
        _expenseByIdFromCache.value = Event(getExpenseByIdFromCache(id))
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

    private val _updateExpenseState = mutableStateOf(UpdateExpenseState())
    val updateExpenseState:State<UpdateExpenseState> get() = _updateExpenseState

    fun updateExpense(token:String, updateExpenseRequest:UpdateExpenseRequest){
        updateExpenseUseCase(token = token, updateExpenseRequest = updateExpenseRequest).onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _updateExpenseState.value = UpdateExpenseState(updated = true)
                }

                Status.ERROR -> {
                    _updateExpenseState.value = UpdateExpenseState(error = it.message ?: Constants.UNKNOWN_ERROR)
                }

                Status.LOADING -> {
                    _updateExpenseState.value = UpdateExpenseState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}