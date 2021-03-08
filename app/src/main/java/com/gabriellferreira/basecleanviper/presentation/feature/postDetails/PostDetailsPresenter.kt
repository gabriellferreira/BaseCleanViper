package com.gabriellferreira.basecleanviper.presentation.feature.postDetails

import com.gabriellferreira.basecleanviper.domain.post.GetPostInteractor
import com.gabriellferreira.basecleanviper.presentation.feature.core.BasePresenter
import com.gabriellferreira.basecleanviper.presentation.feature.postDetails.entity.PostDetailsViewItem
import com.gabriellferreira.basecleanviper.presentation.feature.postDetails.entity.PostDetailsViewItemMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostDetailsPresenter @Inject constructor(
    private val getPostInteractor: GetPostInteractor,
    private val mapper: PostDetailsViewItemMapper
) : BasePresenter<PostDetailsContract.View, PostDetailsContract.Router>(),
    PostDetailsContract.Presenter {

    override fun fetch(postId: String) {
        launch {
            getPostInteractor(postId)
                .onStart {
                    view?.showProgress()
                }
                .map {
                    mapper.map(it)
                }
                .catch {
                    view?.hideProgress()
                    view?.showToast(it.message.toString())
                }
                .collect {
                    view?.hideProgress()
                    view?.setupView(it)
                }
        }
    }
}
