package com.example.template.common.di

import com.example.template.App
import com.example.template.common.di.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AndroidInjector<App>
    }
}