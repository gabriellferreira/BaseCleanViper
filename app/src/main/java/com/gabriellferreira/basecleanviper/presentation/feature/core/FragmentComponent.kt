package com.gabriellferreira.basecleanviper.presentation.feature.core

import com.gabriellferreira.basecleanviper.presentation.app.scope.ControllerScope
import com.gabriellferreira.basecleanviper.presentation.component.ComponentModule
import com.gabriellferreira.basecleanviper.presentation.feature.postList.PostListFragment
import com.gabriellferreira.basecleanviper.presentation.feature.postList.PostListModule
import dagger.Subcomponent

@ControllerScope
@Subcomponent(
    modules = [
        FragmentModule::class,
        ComponentModule::class,
        PostListModule::class,
    ]
)

interface FragmentComponent {
    fun inject(fragment: PostListFragment)
}