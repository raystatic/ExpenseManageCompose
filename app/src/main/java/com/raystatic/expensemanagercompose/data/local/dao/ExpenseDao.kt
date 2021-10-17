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

    @Query("SELECT * FROM expenses WHERE id=:expenseId")
    suspend fun getExpenseById(expenseId: Int): Expense

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    suspend fun getAllExpenses(): List<Expense>?

    @Query("DELETE FROM expenses where id=:expenseId")
    suspend fun deleteExpenseById(expenseId:Int)

    @Query("UPDATE expenses SET title=:title, amount=:amount, date=:date WHERE id=:expenseId")
    suspend fun updateExpense(title:String, amount:Float,date:String, expenseId: Int)

    @Query("DELETE FROM expenses")
    suspend fun deleteAllExpenses()

}