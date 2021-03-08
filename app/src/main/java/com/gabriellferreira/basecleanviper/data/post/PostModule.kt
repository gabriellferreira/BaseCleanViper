package com.gabriellferreira.basecleanviper.data.post

import com.gabriellferreira.basecleanviper.data.post.network.ApolloPostApi
import com.gabriellferreira.basecleanviper.data.post.network.PostApi
import com.gabriellferreira.basecleanviper.domain.post.PostRepository
import dagger.Binds
import dagger.Module

@Module(includes = [PostModule.Declarations::class])
class PostModule {

    @Module
    internal interface Declarations {
        @Binds
        fun bindPostRepository(dataRepository: PostDataRepository): PostRepository

        @Binds
        fun bindPostApi(postApi: ApolloPostApi): PostApi
    }
}