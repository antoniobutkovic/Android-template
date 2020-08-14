package com.example.template.di

import com.example.template.ui.auth.AuthActivity
import com.example.template.di.auth.AuthFragmentBuildersModule
import com.example.template.di.auth.AuthModule
import com.example.template.di.auth.AuthScope
import com.example.template.di.auth.AuthViewModelModule
import com.example.template.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @AuthScope
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}