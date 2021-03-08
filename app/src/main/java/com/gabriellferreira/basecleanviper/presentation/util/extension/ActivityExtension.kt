package com.gabriellferreira.basecleanviper.presentation.util.extension

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.gabriellferreira.basecleanviper.presentation.feature.core.ActivityComponent
import com.gabriellferreira.basecleanviper.presentation.feature.core.ActivityModule

val Activity.controllerComponent: ActivityComponent
    get() = applicationContext.applicationComponent.newActivityComponent(
        ActivityModule(this)
    )

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}