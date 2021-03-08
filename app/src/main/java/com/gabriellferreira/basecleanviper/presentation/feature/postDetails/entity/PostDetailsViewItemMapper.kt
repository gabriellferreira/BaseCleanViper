package com.gabriellferreira.basecleanviper.presentation.feature.postDetails.entity

import android.content.res.Resources
import com.gabriellferreira.basecleanviper.R
import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import javax.inject.Inject

class PostDetailsViewItemMapper @Inject constructor(
    private val resources: Resources
) {

    fun map(post: Post): PostDetailsViewItem {
        val toolbarTitle = resources.getString(R.string.post_details_toolbar_title, post.id)
        val title = post.title
        val body = post.body

        return PostDetailsViewItem(
            toolbarTitle = toolbarTitle,
            title = title,
            body = body,
            userInfo = resources.getString(
                R.string.post_details_user_info,
                post.user.name,
                post.user.username
            ),
        )
    }
}