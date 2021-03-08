package com.gabriellferreira.basecleanviper.presentation.app

import com.gabriellferreira.basecleanviper.data.post.PostModule
import com.gabriellferreira.basecleanviper.presentation.app.coroutines.CoroutinesModule
import com.gabriellferreira.basecleanviper.presentation.app.network.ApolloModule
import com.gabriellferreira.basecleanviper.presentation.app.network.RetrofitModule
import com.gabriellferreira.basecleanviper.presentation.app.scope.ApplicationScope
import com.gabriellferreira.basecleanviper.presentation.feature.core.ActivityComponent
import com.gabriellferreira.basecleanviper.presentation.feature.core.ActivityModule
import com.gabriellferreira.basecleanviper.presentation.feature.core.FragmentComponent
import com.gabriellferreira.basecleanviper.presentation.feature.core.FragmentModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        RetrofitModule::class,
        ApolloModule::class,
        CoroutinesModule::class,
        PostModule::class,
    ]
)
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
    fun newFragmentComponent(fragmentModule: FragmentModule): FragmentComponent
}