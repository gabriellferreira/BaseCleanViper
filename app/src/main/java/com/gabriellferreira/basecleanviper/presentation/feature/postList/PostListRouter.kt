package com.gabriellferreira.basecleanviper.presentation.feature.postList

import android.app.Activity
import com.gabriellferreira.basecleanviper.presentation.feature.postDetails.PostDetailsActivity
import javax.inject.Inject

class PostListRouter @Inject constructor(
    private val activity: Activity
) : PostListContract.Router{

    override fun navigateToPostDetails(postId: String) {
        activity.startActivity(PostDetailsActivity.createIntent(activity, postId))
    }
}