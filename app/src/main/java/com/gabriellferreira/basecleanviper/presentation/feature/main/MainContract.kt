package com.gabriellferreira.basecleanviper.presentation.feature.main

import com.gabriellferreira.basecleanviper.presentation.feature.core.BaseContract

interface MainContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>

    interface Router : BaseContract.Router
}