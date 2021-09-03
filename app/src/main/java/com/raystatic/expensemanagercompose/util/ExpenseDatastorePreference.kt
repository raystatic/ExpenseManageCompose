package com.raystatic.expensemanagercompose.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExpenseDatastorePreference(
    private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

    private val jwtPreferenceKey = stringPreferencesKey(Constants.USERTOKENKEY)

    val jwtToken: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[jwtPreferenceKey] ?: ""
        }

    suspend fun updateJWTToken(jwt:String){
        context.dataStore.edit {preferences->
            preferences[jwtPreferenceKey] = jwt
        }
    }

}