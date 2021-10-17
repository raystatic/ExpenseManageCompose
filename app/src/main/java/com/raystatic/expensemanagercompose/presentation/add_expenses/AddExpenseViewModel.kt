package com.raystatic.expensemanagercompose.presentation.add_expenses

import androidx.lifecycle.*
import com.raystatic.expensemanagercompose.data.remote.dto.AddExpenseRequest
import com.raystatic.expensemanagercompose.data.remote.dto.DeleteExpenseResponse
import com.raystatic.expensemanagercompose.data.remote.dto.UpdateExpenseRequest
import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.domain.usecases.expenses.AddExpenseUseCase
import com.raystatic.expensemanagercompose.domain.usecases.expenses.DeleteExpenseByIdUseCase
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
    private val deleteExpenseByIdUseCase: DeleteExpenseByIdUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _deleteExpenseState = MutableLiveData<Event<DeleteExpenseState>>()
    val deleteExpenseState : LiveData<Event<DeleteExpenseState>> get() = _deleteExpenseState

    fun deleteExpenseById(expenseId:Int){
        deleteExpenseByIdUseCase(expenseId = expenseId).onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _deleteExpenseState.value = Event(DeleteExpenseState(expenseDeleted = it.data?.error == false, message = it.data?.message ?: ""))
                }

                Status.ERROR -> {
                    _deleteExpenseState.value = Event(DeleteExpenseState(expenseDeleted = false, message = it.message ?: Constants.UNKNOWN_ERROR))
                }

                Status.LOADING -> {
                    _deleteExpenseState.value = Event(DeleteExpenseState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _expenseByIdFromCache = SingleLiveEvent<Event<Expense>>()
    val expenseByIdFromCache:SingleLiveEvent<Event<Expense>> get() = _expenseByIdFromCache

    init {
        savedStateHandle.get<String>(Constants.EXPENSE_ID)?.let {
            if (it.toInt() != -1){
                getExpenseFromCache(id = it.toInt())
            }
        }
    }

    private fun getExpenseFromCache(id:Int) = viewModelScope.launch {
        _expenseByIdFromCache.value = Event(getExpenseByIdFromCache(id))
    }

    private val _addExpenseState = MutableLiveData<Event<AddExpenseState>>()
    val addExpenseState:LiveData<Event<AddExpenseState>> get() = _addExpenseState

    fun addExpense(addExpenseRequest: AddExpenseRequest, token:String){
        addExpenseUseCase(addExpenseRequest, token = token).onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _addExpenseState.value = Event(AddExpenseState(addedExpense = it.data))
                }

                Status.ERROR -> {
                    _addExpenseState.value = Event(AddExpenseState(error = it.message ?: Constants.UNKNOWN_ERROR))
                }

                Status.LOADING -> {
                    _addExpenseState.value = Event(AddExpenseState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _updateExpenseState = MutableLiveData<Event<UpdateExpenseState>>()
    val updateExpenseState:LiveData<Event<UpdateExpenseState>> get() = _updateExpenseState

    fun updateExpense(token:String, updateExpenseRequest:UpdateExpenseRequest){
        updateExpenseUseCase(token = token, updateExpenseRequest = updateExpenseRequest).onEach {
            when(it.status){
                Status.SUCCESS -> {
                    _updateExpenseState.value = Event(UpdateExpenseState(updated = true))
                }

                Status.ERROR -> {
                    _updateExpenseState.value = Event(UpdateExpenseState(error = it.message ?: Constants.UNKNOWN_ERROR))
                }

                Status.LOADING -> {
                    _updateExpenseState.value = Event(UpdateExpenseState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

}