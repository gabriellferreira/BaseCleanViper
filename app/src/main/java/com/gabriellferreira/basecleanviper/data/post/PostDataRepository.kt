package com.gabriellferreira.basecleanviper.data.post

import com.gabriellferreira.basecleanviper.domain.post.mapper.PostMapper
import com.gabriellferreira.basecleanviper.data.post.network.PostApi
import com.gabriellferreira.basecleanviper.domain.util.mapper.PageMapper
import com.gabriellferreira.basecleanviper.domain.post.PostRepository
import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import com.gabriellferreira.basecleanviper.domain.util.entity.Page
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostDataRepository @Inject constructor(
    private val api: PostApi,
    private val postMapper: PostMapper,
    private val pageMapper: PageMapper,
) : PostRepository {

    override fun get(id: String) : Flow<Post> =
        api.fetch(id).map {
            val error = it.errors?.firstOrNull()
            val data = it.data?.post?.fragments?.post
            return@map if (error == null && data != null) {
                postMapper.map(data)
            } else {
                throw Throwable(error?.message)
            }
        }

    override fun getList(
        pageNumber: Int,
        pageSize: Int
    ): Flow<Page<Post>> =
        api.fetchAll(pageNumber, pageSize).map {
            val error = it.errors?.firstOrNull()
            val data = it.data
            return@map if (error == null && data != null) {
                pageMapper.map(data)
            } else {
                throw Throwable(error?.message)
            }
        }
}