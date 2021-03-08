package com.gabriellferreira.basecleanviper.presentation.feature.core

import android.app.Activity
import com.gabriellferreira.basecleanviper.presentation.app.scope.ControllerScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val activity: Activity
) {

    @Provides
    @ControllerScope
    fun provideActivity(): Activity {
        return activity
    }
}