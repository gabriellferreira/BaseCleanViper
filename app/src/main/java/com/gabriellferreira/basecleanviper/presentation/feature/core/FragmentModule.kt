package com.gabriellferreira.basecleanviper.presentation.feature.core

import android.app.Activity
import androidx.fragment.app.Fragment
import com.gabriellferreira.basecleanviper.presentation.app.scope.ControllerScope
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(
    private val fragment: Fragment
) {

    @Provides
    @ControllerScope
    fun provideFragment(): Fragment {
        return fragment
    }

    @Provides
    @ControllerScope
    fun provideFragmentActivity(): Activity {
        return fragment.requireActivity()
    }
}