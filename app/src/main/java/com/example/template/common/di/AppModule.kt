package com.example.template.common.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.template.App
import com.example.template.BuildConfig
import com.example.template.R
import com.example.template.common.api.ApiInterceptor
import com.example.template.common.data.db.AppDatabase
import com.example.template.common.data.db.AppDatabase.Companion.DATABASE_NAME
import com.example.template.utils.BASE_URL
import com.example.template.common.data.prefs.PrefsManager
import com.example.template.common.session.SessionManager
import com.example.template.features.auth.data.db.AccountPropertiesDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule{

    @Provides
    @JvmStatic
    @Singleton
    fun provideApplication(app: App): Application = app

    @Provides
    @JvmStatic
    @Singleton
    fun provideAppContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideSessionManager(prefsManager: PrefsManager): SessionManager {
        return SessionManager(prefsManager)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun providePrefsManager(application: Application): PrefsManager {
        return PrefsManager(application)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideApiInterceptor(prefsManager: PrefsManager): ApiInterceptor {
        return ApiInterceptor(prefsManager)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, apiInterceptor: ApiInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) client.addInterceptor(loggingInterceptor)
        client.addInterceptor(apiInterceptor)
        return client.build()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson, client: OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideAccountPropertiesDao(appDatabase: AppDatabase): AccountPropertiesDao {
        return appDatabase.getAccountPropertiesDao()
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

}