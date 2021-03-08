package com.gabriellferreira.basecleanviper.presentation.feature.postList.pagingSource

import androidx.paging.*
import com.gabriellferreira.basecleanviper.domain.post.GetPostListInteractor
import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PostPagingSource @Inject constructor(
    private val getPostListInteractor: GetPostListInteractor,
) : PagingSource<Int, Post>() {

    operator fun invoke(
        initPage: Int = 1
    ): Flow<PagingData<Post>> {
        val pageSize = GetPostListInteractor.POST_LIST_PAGE_SIZE

        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
                initialLoadSize = pageSize
            ),
            initialKey = initPage
        ) {
            this
        }.flow
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val result = getPostListInteractor(
                pageNumber = params.key ?: 1,
            ).first()

            LoadResult.Page(
                result.itemList,
                result.previousPage,
                result.nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it) }?.nextKey
    }
}