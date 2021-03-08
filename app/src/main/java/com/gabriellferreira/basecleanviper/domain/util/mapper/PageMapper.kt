package com.gabriellferreira.basecleanviper.domain.util.mapper

import com.gabriellferreira.basecleanviper.domain.post.mapper.PostMapper
import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import com.gabriellferreira.basecleanviper.domain.util.entity.Page
import com.gabriellferreira.basecleanviper.query.GetPostListQuery
import javax.inject.Inject

class PageMapper @Inject constructor(
    private val postMapper: PostMapper
) {

    fun map(data: GetPostListQuery.Data): Page<Post> {
        val links = data.posts?.links
        val previousPage = links?.prev?.fragments?.pageLimitPair
        val nextPage = links?.next?.fragments?.pageLimitPair
        val list = data.posts?.data?.map {
            postMapper.map(it?.fragments?.post ?: throw Exception("Invalid Post structure"))
        }

        return Page(
            previousPage = previousPage?.page,
            nextPage = nextPage?.page,
            itemList = list ?: emptyList(),
        )
    }
}