package com.raystatic.expensemanagercompose.di

import com.raystatic.expensemanagercompose.data.local.dao.ExpenseDao
import com.raystatic.expensemanagercompose.data.local.dao.UserDao
import com.raystatic.expensemanagercompose.data.remote.ApiService
import com.raystatic.expensemanagercompose.data.repositories.ExpenseRepositoryImpl
import com.raystatic.expensemanagercompose.data.repositories.UserRepositoryImpl
import com.raystatic.expensemanagercompose.domain.repositories.ExpenseRepository
import com.raystatic.expensemanagercompose.domain.repositories.UserRepository
import com.raystatic.expensemanagercompose.util.PrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        apiService: ApiService,
        userDao: UserDao,
        prefManager: PrefManager
    ):UserRepository{
        return UserRepositoryImpl(apiService,userDao, prefManager = prefManager)
    }

    @Singleton
    @Provides
    fun provideExpenseRepository(
        apiService: ApiService,
        expenseDao: ExpenseDao,
        prefManager: PrefManager
    ):ExpenseRepository{
        return ExpenseRepositoryImpl(apiService,expenseDao, prefManager = prefManager)
    }

}