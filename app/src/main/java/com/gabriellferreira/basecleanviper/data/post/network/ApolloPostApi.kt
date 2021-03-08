package com.gabriellferreira.basecleanviper.data.post.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toFlow
import com.gabriellferreira.basecleanviper.query.GetPostListQuery
import com.gabriellferreira.basecleanviper.query.GetPostQuery
import com.gabriellferreira.basecleanviper.type.PageQueryOptions
import com.gabriellferreira.basecleanviper.type.PaginateOptions
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApolloPostApi @Inject constructor(
    private val apolloClient: ApolloClient
) : PostApi {

    override fun fetch(id: String): Flow<Response<GetPostQuery.Data>> =
        apolloClient.query(
            GetPostQuery(id)
        ).toFlow()

    override fun fetchAll(
        pageNumber: Int?,
        pageLimit: Int?
    ): Flow<Response<GetPostListQuery.Data>> {
        val options = PageQueryOptions(
            paginate = Input.optional(
                PaginateOptions(
                    page = Input.optional(pageNumber),
                    limit = Input.optional(pageLimit)
                )
            )
        )

        return apolloClient.query(
            GetPostListQuery(Input.optional(options))
        ).toFlow()
    }
}