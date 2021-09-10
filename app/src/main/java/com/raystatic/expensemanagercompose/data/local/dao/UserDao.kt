package com.raystatic.expensemanagercompose.data.local.dao

import androidx.room.*
import com.raystatic.expensemanagercompose.domain.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getUser():Flow<User>

    @Query("DELETE FROM user")
    suspend fun deleteUser()

}