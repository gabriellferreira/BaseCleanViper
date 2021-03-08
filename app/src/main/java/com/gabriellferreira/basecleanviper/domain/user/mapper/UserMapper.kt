package com.gabriellferreira.basecleanviper.domain.user.mapper

import com.gabriellferreira.basecleanviper.domain.user.entity.User
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun map(user: com.gabriellferreira.basecleanviper.fragment.User) =
        with(user) {
            User(
                id = id ?: throw Exception("Invalid User id"),
                name = name ?: "",
                username = username ?: "",
                email = email ?: "",
            )
        }
}