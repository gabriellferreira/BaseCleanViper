package com.gabriellferreira.basecleanviper.presentation.feature.main

import dagger.Binds
import dagger.Module

@Module
interface MainModule {

    @Binds
    fun bindMainPresenter(presenter: MainPresenter): MainContract.Presenter

    @Binds
    fun bindMainRouter(router: MainRouter): MainContract.Router
}
