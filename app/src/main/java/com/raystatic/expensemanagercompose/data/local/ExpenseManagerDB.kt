package com.raystatic.expensemanagercompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raystatic.expensemanagercompose.data.local.dao.UserDao
import com.raystatic.expensemanagercompose.domain.models.User


@Database(
    entities = [User::class],
    version = 1
)
abstract class ExpenseManagerDB: RoomDatabase() {

    abstract fun getUserDao():UserDao

}