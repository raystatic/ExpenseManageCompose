package com.raystatic.expensemanagercompose.domain.repositories

import com.raystatic.expensemanagercompose.data.remote.dto.LoginRequestBody
import com.raystatic.expensemanagercompose.domain.models.User
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser():Flow<User>

    fun authUser(loginRequestBody: LoginRequestBody):Flow<Resource<Boolean?>>

}