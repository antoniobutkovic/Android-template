package com.example.template.features.auth.di

import com.example.template.features.auth.LoginFragment
import com.example.template.features.auth.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

}