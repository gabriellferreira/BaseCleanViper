package com.gabriellferreira.basecleanviper.presentation.app

import android.app.Application

class AppApplication : Application() {

    val applicationComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}