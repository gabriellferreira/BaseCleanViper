package com.gabriellferreira.basecleanviper.presentation.feature.postList

import androidx.paging.PagingData
import com.gabriellferreira.basecleanviper.presentation.feature.core.BaseContract
import com.gabriellferreira.basecleanviper.presentation.feature.postList.entity.PostItem

interface PostListContract {

    interface View : BaseContract.View {
        fun showItems(list: PagingData<PostItem>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getPostList()
        fun onPostItemClicked(item: PostItem)
    }

    interface Router : BaseContract.Router {
        fun navigateToPostDetails(postId: String)
    }
}