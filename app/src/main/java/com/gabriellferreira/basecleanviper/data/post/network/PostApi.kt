package com.gabriellferreira.basecleanviper.data.post.network

import com.apollographql.apollo.api.Response
import com.gabriellferreira.basecleanviper.query.GetPostListQuery
import com.gabriellferreira.basecleanviper.query.GetPostQuery
import kotlinx.coroutines.flow.Flow

interface PostApi {

    fun fetch(
        id: String
    ): Flow<Response<GetPostQuery.Data>>

    fun fetchAll(
        pageNumber: Int?,
        pageLimit: Int?
    ): Flow<Response<GetPostListQuery.Data>>
}