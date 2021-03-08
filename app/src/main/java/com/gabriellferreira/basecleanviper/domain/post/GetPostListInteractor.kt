package com.gabriellferreira.basecleanviper.domain.post

import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import com.gabriellferreira.basecleanviper.domain.util.entity.Page
import com.gabriellferreira.basecleanviper.presentation.app.coroutines.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPostListInteractor @Inject constructor(
    private val postRepository: PostRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(pageNumber: Int = 1): Flow<Page<Post>> =
        postRepository.getList(
            pageNumber = pageNumber,
            pageSize = POST_LIST_PAGE_SIZE,
        )
            .flowOn(dispatcher)

    companion object {
        const val POST_LIST_PAGE_SIZE = 10
    }
}