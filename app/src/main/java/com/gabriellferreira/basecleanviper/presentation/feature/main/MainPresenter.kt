package com.gabriellferreira.basecleanviper.presentation.feature.main

import com.gabriellferreira.basecleanviper.presentation.feature.core.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor() :
    BasePresenter<MainContract.View, MainContract.Router>(),
    MainContract.Presenter