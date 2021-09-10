package com.raystatic.expensemanagercompose.data.repositories

import com.raystatic.expensemanagercompose.data.local.dao.UserDao
import com.raystatic.expensemanagercompose.data.remote.ApiService
import com.raystatic.expensemanagercompose.data.remote.dto.LoginRequestBody
import com.raystatic.expensemanagercompose.domain.models.User
import com.raystatic.expensemanagercompose.domain.repositories.UserRepository
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.ExpenseDatastorePreference
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val datastorePreference: ExpenseDatastorePreference
): UserRepository {

    override fun getUser(): Flow<User> {
        return userDao.getUser()
    }

    override fun authUser(loginRequestBody: LoginRequestBody): Flow<Resource<Boolean?>> = flow {
        try {
            emit(Resource.loading(null))
            val response = apiService.auth(loginRequestBody = loginRequestBody)
            if (!response.error){
                response.data?.let {
                    userDao.insertUser(it)
                }
                datastorePreference.updateJWTToken(response.token ?: "")
                emit(Resource.success(true))
            }else{
                emit(Resource.error(response.message,false))
            }
        }catch (e:HttpException){
            e.printStackTrace()
            emit(Resource.error(Constants.SOMETHING_WENT_WRONG, false))
        }catch (e:IOException){
            e.printStackTrace()
            emit(Resource.error(Constants.CHECK_INTERNET_ERROR, false))
        }
    }

}