package com.raystatic.expensemanagercompose.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.raystatic.expensemanagercompose.domain.usecases.expenses.GetExpensesFromCacheUseCase
import com.raystatic.expensemanagercompose.domain.usecases.expenses.GetExpensesFromRemoteUseCase
import com.raystatic.expensemanagercompose.domain.usecases.expenses.GetMonthlyExpensesUseCase
import com.raystatic.expensemanagercompose.domain.usecases.user.GetUserUseCase
import com.raystatic.expensemanagercompose.util.Constants
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

    private val _selectedDuration = mutableStateOf("Daily")
    val selectedDuration:State<String> get() = _selectedDuration

    fun setSelectedDuration(duration:DurationSelector){
        _selectedDuration.value = duration.title
    }

    private val _expenseListState = mutableStateOf(ExpenseListState())
    val expenseListState:State<ExpenseListState> get() = _expenseListState

    fun getExpensesFromRemote(token:String){
        getExpensesFromRemoteUseCase(token = token).onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _expenseListState.value = ExpenseListState(expensesUpdated = it.data == true)
                }
                Status.ERROR -> {
                    _expenseListState.value = ExpenseListState(error = it.message ?: "An error occurred while syncing expenses")
                }
                Status.LOADING -> {
                    _expenseListState.value = ExpenseListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun getExpensesFromCache() = getExpensesFromCacheUseCase()

    private val _monthlyExpensesState = mutableStateOf(MonthlyExpensesState())
    val monthlyExpensesState:State<MonthlyExpensesState> get() = _monthlyExpensesState

    fun getMonthlyExpense(){
        getMonthlyExpensesUseCase().onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _monthlyExpensesState.value = MonthlyExpensesState(monthlyExpense = it.data ?: emptyList())
                }

                Status.ERROR -> {
                    _monthlyExpensesState.value = MonthlyExpensesState(error = it.message ?: Constants.UNKNOWN_ERROR)
                }

                Status.LOADING -> {
                    _monthlyExpensesState.value = MonthlyExpensesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



}