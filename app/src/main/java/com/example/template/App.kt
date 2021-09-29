package com.example.template

import com.example.template.common.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}
