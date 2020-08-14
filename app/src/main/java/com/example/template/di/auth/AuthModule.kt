package com.example.template.di.auth

import com.example.template.api.ApiAuthService
import com.example.template.persistence.AccountPropertiesDao
import com.example.template.persistence.AppDatabase
import com.example.template.repository.AuthRepository
import com.example.template.session.SessionManager
import com.example.template.utils.PrefsManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideOpenApiAuthService(retrofitBuilder: Retrofit.Builder): ApiAuthService {
        return retrofitBuilder
            .build()
            .create(ApiAuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(database: AppDatabase, apiAuthService: ApiAuthService): AuthRepository {
        return AuthRepository(apiAuthService, database)
    }

}