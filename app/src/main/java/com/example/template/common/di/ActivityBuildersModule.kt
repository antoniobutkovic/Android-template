package com.example.template.common.di

import com.example.template.features.auth.AuthActivity
import com.example.template.features.main.di.MainScope
import com.example.template.features.auth.di.AuthFragmentBuildersModule
import com.example.template.features.auth.di.AuthModule
import com.example.template.features.auth.di.AuthScope
import com.example.template.features.auth.di.AuthViewModelModule
import com.example.template.features.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}