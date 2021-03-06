package com.raystatic.expensemanagercompose.di

import android.content.Context
import android.content.pm.PackageManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.raystatic.expensemanagercompose.util.PrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGoogleSignInOptions(@ApplicationContext context: Context): GoogleSignInOptions {

        val clientId = context.packageManager
            .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            .metaData["google_auth_client"].toString()
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }

    @Singleton
    @Provides
    fun providesGoogleSignInClient(@ApplicationContext context: Context,googleSignInOptions: GoogleSignInOptions)
        = GoogleSignIn.getClient(context, googleSignInOptions)

    @Singleton
    @Provides
    fun providesExpensePrefManager(
        @ApplicationContext context: Context
    ) = PrefManager(context = context)


}