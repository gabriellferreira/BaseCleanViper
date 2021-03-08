package com.gabriellferreira.basecleanviper.presentation.util.extension

import android.content.Context
import com.gabriellferreira.basecleanviper.presentation.app.AppApplication
import com.gabriellferreira.basecleanviper.presentation.app.AppComponent

val Context.applicationComponent: AppComponent
    get() {
        applicationContext
            ?: throw IllegalArgumentException("App component can be obtained only from app context")
        return (applicationContext as AppApplication).applicationComponent
    }