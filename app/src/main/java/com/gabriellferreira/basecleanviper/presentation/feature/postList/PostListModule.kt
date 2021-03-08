package com.gabriellferreira.basecleanviper.presentation.feature.postList

import dagger.Binds
import dagger.Module

@Module
interface PostListModule {

    @Binds
    fun bindPostListPresenter(presenter: PostListPresenter): PostListContract.Presenter

    @Binds
    fun bindPostListRouter(router: PostListRouter): PostListContract.Router
}
