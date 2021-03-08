package com.gabriellferreira.basecleanviper.presentation.feature.postDetails

import com.gabriellferreira.basecleanviper.presentation.feature.core.BaseContract
import com.gabriellferreira.basecleanviper.presentation.feature.postDetails.entity.PostDetailsViewItem

interface PostDetailsContract {

    interface View : BaseContract.View {
        fun setupView(item: PostDetailsViewItem)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun fetch(postId: String)
    }

    interface Router : BaseContract.Router
}