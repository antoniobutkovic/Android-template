package com.example.template.features.auth.di

import androidx.lifecycle.ViewModel
import com.example.template.common.di.viewmodel.ViewModelKey
import com.example.template.features.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}