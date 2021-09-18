package com.raystatic.expensemanagercompose.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.raystatic.expensemanagercompose.data.local.dao.ExpenseDao
import com.raystatic.expensemanagercompose.data.local.dao.UserDao
import com.raystatic.expensemanagercompose.domain.models.Expense
import com.raystatic.expensemanagercompose.domain.models.User


@Database(
    entities = [User::class, Expense::class],
    version = 1
)
abstract class ExpenseManagerDB: RoomDatabase() {

    abstract fun getUserDao():UserDao

    abstract fun getExpenseDao():ExpenseDao

}