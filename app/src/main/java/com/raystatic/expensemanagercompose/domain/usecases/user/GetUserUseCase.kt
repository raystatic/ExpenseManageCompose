package com.raystatic.expensemanagercompose.domain.usecases.user

import com.raystatic.expensemanagercompose.domain.models.User
import com.raystatic.expensemanagercompose.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke():Flow<User>{
        return userRepository.getUser()
    }

}