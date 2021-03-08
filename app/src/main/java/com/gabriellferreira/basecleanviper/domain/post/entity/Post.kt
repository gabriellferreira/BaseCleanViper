package com.gabriellferreira.basecleanviper.domain.post.entity

import com.gabriellferreira.basecleanviper.domain.user.entity.User

class Post(
    val id: String,
    val title: String,
    val body: String,
    val user: User
)
