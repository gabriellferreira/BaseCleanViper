package com.gabriellferreira.basecleanviper.presentation.feature.postList.entity

import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import javax.inject.Inject

class PostItemMapper @Inject constructor() {

    fun map(item: Post): PostItem =
        PostItem(
            id = item.id,
            title = item.title,
            body = item.body.take(BODY_CHAR_SIZE)
        )

    companion object {
        const val BODY_CHAR_SIZE = 120
    }
}