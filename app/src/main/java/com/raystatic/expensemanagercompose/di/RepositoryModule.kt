package com.raystatic.expensemanagercompose.di

import com.raystatic.expensemanagercompose.data.local.dao.UserDao
import com.raystatic.expensemanagercompose.data.remote.ApiService
import com.raystatic.expensemanagercompose.data.repositories.UserRepositoryImpl
import com.raystatic.expensemanagercompose.domain.repositories.UserRepository
import com.raystatic.expensemanagercompose.util.ExpenseDatastorePreference
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
        datastorePreference: ExpenseDatastorePreference
    ):UserRepository{
        return UserRepositoryImpl(apiService,userDao, datastorePreference)
    }

}