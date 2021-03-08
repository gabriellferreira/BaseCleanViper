package com.gabriellferreira.basecleanviper.domain.post

import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import com.gabriellferreira.basecleanviper.domain.util.entity.Page
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun get(id: String) : Flow<Post>

    fun getList(
        pageNumber: Int = 1,
        pageSize: Int
    ): Flow<Page<Post>>
}
