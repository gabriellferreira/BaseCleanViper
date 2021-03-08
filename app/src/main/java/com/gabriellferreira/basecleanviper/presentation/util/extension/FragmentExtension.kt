package com.gabriellferreira.basecleanviper.presentation.util.extension

import androidx.fragment.app.Fragment
import com.gabriellferreira.basecleanviper.presentation.feature.core.FragmentComponent
import com.gabriellferreira.basecleanviper.presentation.feature.core.FragmentModule

val Fragment.controllerComponent: FragmentComponent
    get() = requireContext().applicationComponent.newFragmentComponent(FragmentModule(this))