package com.example.template.features.auth.di

import com.example.template.common.api.ApiAuthService
import com.example.template.common.data.db.AppDatabase
import com.example.template.features.auth.repository.AuthRepository
import com.example.template.features.auth.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideApiAuthService(retrofitBuilder: Retrofit.Builder): ApiAuthService {
        return retrofitBuilder
            .build()
            .create(ApiAuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(database: AppDatabase, apiAuthService: ApiAuthService): AuthRepository {
        return AuthRepositoryImpl(apiAuthService, database)
    }

}