package com.raystatic.expensemanagercompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raystatic.expensemanagercompose.domain.models.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpensesFlow(): Flow<List<Expense>>

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    suspend fun getAllExpenses(): List<Expense>

    @Query("DELETE FROM expenses where id=:expenseId")
    suspend fun deleteExpenseById(expenseId:Int)

}