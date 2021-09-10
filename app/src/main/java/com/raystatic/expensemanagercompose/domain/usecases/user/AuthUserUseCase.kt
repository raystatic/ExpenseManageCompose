package com.raystatic.expensemanagercompose.domain.usecases.user

import android.util.Log
import com.raystatic.expensemanagercompose.data.remote.dto.LoginRequestBody
import com.raystatic.expensemanagercompose.domain.repositories.UserRepository
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AuthUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(loginRequestBody: LoginRequestBody): Flow<Resource<Boolean?>> {
        return userRepository.authUser(loginRequestBody)
    }
}