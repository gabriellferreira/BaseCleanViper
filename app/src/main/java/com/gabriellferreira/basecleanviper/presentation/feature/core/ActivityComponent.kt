package com.gabriellferreira.basecleanviper.presentation.feature.core

import com.gabriellferreira.basecleanviper.presentation.app.scope.ControllerScope
import com.gabriellferreira.basecleanviper.presentation.component.ComponentModule
import com.gabriellferreira.basecleanviper.presentation.feature.main.MainActivity
import com.gabriellferreira.basecleanviper.presentation.feature.main.MainModule
import com.gabriellferreira.basecleanviper.presentation.feature.postDetails.PostDetailsActivity
import com.gabriellferreira.basecleanviper.presentation.feature.postDetails.PostDetailsModule
import dagger.Subcomponent

@ControllerScope
@Subcomponent(
    modules = [
        ActivityModule::class,
        ComponentModule::class,
        MainModule::class,
        PostDetailsModule::class,
    ]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: PostDetailsActivity)
}