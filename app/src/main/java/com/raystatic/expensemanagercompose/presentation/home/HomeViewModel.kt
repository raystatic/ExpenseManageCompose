package com.raystatic.expensemanagercompose.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.raystatic.expensemanagercompose.domain.usecases.expenses.GetExpensesFromCacheUseCase
import com.raystatic.expensemanagercompose.domain.usecases.expenses.GetExpensesFromRemoteUseCase
import com.raystatic.expensemanagercompose.domain.usecases.expenses.GetMonthlyExpensesUseCase
import com.raystatic.expensemanagercompose.domain.usecases.user.GetUserUseCase
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.Event
import com.raystatic.expensemanagercompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getExpensesFromRemoteUseCase:GetExpensesFromRemoteUseCase,
    private val getExpensesFromCacheUseCase: GetExpensesFromCacheUseCase,
    private val getMonthlyExpensesUseCase:GetMonthlyExpensesUseCase
) : ViewModel() {

    val user = getUserUseCase().asLiveData()

    init {
        getExpensesFromRemote()
    }

    private val _selectedDuration = mutableStateOf("Daily")
    val selectedDuration:State<String> get() = _selectedDuration

    fun setSelectedDuration(duration:DurationSelector){
        _selectedDuration.value = duration.title
    }

    private val _expenseListState = MutableLiveData<Event<ExpenseListState>>()
    val expenseListState:LiveData<Event<ExpenseListState>> get() = _expenseListState

    private fun getExpensesFromRemote(){
        getExpensesFromRemoteUseCase().onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _expenseListState.value = Event(ExpenseListState(expensesUpdated = it.data == true))
                }
                Status.ERROR -> {
                    _expenseListState.value = Event(ExpenseListState(error = it.message ?: "An error occurred while syncing expenses"))
                }
                Status.LOADING -> {
                    _expenseListState.value = Event(ExpenseListState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)

    }

    fun getExpensesFromCache() = getExpensesFromCacheUseCase()

    private val _monthlyExpensesState = MutableLiveData<Event<MonthlyExpensesState>>()
    val monthlyExpensesState:LiveData<Event<MonthlyExpensesState>> get() = _monthlyExpensesState

    fun getMonthlyExpense(){
        getMonthlyExpensesUseCase().onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _monthlyExpensesState.value = Event(MonthlyExpensesState(monthlyExpense = it.data ?: emptyList()))
                }

                Status.ERROR -> {
                    _monthlyExpensesState.value = Event(MonthlyExpensesState(error = it.message ?: Constants.UNKNOWN_ERROR))
                }

                Status.LOADING -> {
                    _monthlyExpensesState.value = Event(MonthlyExpensesState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }



}