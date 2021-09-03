package com.raystatic.expensemanagercompose.di

import android.content.Context
import androidx.room.Room
import com.raystatic.expensemanagercompose.data.local.ExpenseManagerDB
import com.raystatic.expensemanagercompose.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ExpenseManagerDB::class.java,
        Constants.ExpenseManagerDB
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(db: ExpenseManagerDB) = db.getUserDao()

}