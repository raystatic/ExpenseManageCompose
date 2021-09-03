package com.raystatic.expensemanagercompose.data.repositories

import com.raystatic.expensemanagercompose.data.local.dao.UserDao
import com.raystatic.expensemanagercompose.data.remote.ApiService
import com.raystatic.expensemanagercompose.data.remote.requests.LoginRequestBody
import com.raystatic.expensemanagercompose.util.Constants
import com.raystatic.expensemanagercompose.util.ExpenseDatastorePreference
import com.raystatic.expensemanagercompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val datastorePreference: ExpenseDatastorePreference
) {

    suspend fun authUser(loginRequestBody: LoginRequestBody): Flow<Resource<Boolean>> = flow {
        emit(Resource.loading(null))
        try {
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
        }catch (e:Exception){
            e.printStackTrace()
            emit(Resource.error(Constants.SOMETHING_WENT_WRONG, false))
        }
    }

}