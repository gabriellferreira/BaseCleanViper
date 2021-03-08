package com.gabriellferreira.basecleanviper.presentation.feature.postList

import androidx.paging.cachedIn
import androidx.paging.map
import com.gabriellferreira.basecleanviper.presentation.feature.core.BasePresenter
import com.gabriellferreira.basecleanviper.presentation.feature.postList.entity.PostItem
import com.gabriellferreira.basecleanviper.presentation.feature.postList.entity.PostItemMapper
import com.gabriellferreira.basecleanviper.presentation.feature.postList.pagingSource.PostPagingSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostListPresenter @Inject constructor(
    private val postPagingSource: PostPagingSource,
    private val mapper: PostItemMapper,
) : BasePresenter<PostListContract.View, PostListContract.Router>(),
    PostListContract.Presenter {

    override fun getPostList() {
        launch {
            postPagingSource()
                .cachedIn(this)
                .catch {
                    view?.showToast(it.message.toString())
                }
                .collect {
                    val result = it.map { post ->
                        mapper.map(post)
                    }
                    view?.showItems(result)
                }
        }
    }

    override fun onPostItemClicked(item: PostItem) {
        router.navigateToPostDetails(item.id)
    }
}