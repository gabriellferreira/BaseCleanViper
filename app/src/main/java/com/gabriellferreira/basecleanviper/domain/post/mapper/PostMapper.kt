package com.gabriellferreira.basecleanviper.domain.post.mapper

import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import com.gabriellferreira.basecleanviper.domain.user.mapper.UserMapper
import javax.inject.Inject

class PostMapper @Inject constructor(
    private val userMapper: UserMapper
) {

    fun map(data: com.gabriellferreira.basecleanviper.fragment.Post): Post =
        with(data) {
            Post(
                id = id ?: throw Exception("Invalid Post id"),
                title = title ?: "",
                body = body ?: "",
                user = userMapper.map(data.user?.fragments?.user ?: throw Exception("Invalid User"))
            )
        }
}
