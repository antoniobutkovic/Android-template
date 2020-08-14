package com.example.template

import android.app.Application
import com.example.template.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        Thread.sleep(1000) // Development purposes

        DaggerAppComponent.builder().application(this)
            .build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


}
