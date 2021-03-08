package com.gabriellferreira.basecleanviper.presentation.feature.postDetails

import dagger.Binds
import dagger.Module

@Module
interface PostDetailsModule {

    @Binds
    fun bindPostDetailsPresenter(presenter: PostDetailsPresenter): PostDetailsContract.Presenter

    @Binds
    fun bindPostDetailsRouter(router: PostDetailsRouter): PostDetailsContract.Router
}
