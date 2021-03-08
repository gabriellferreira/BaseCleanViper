package com.gabriellferreira.basecleanviper.data.post

import com.apollographql.apollo.api.Error
import com.apollographql.apollo.api.Response
import com.gabriellferreira.basecleanviper.CoroutineSpek
import com.gabriellferreira.basecleanviper.data.post.network.PostApi
import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import com.gabriellferreira.basecleanviper.domain.post.mapper.PostMapper
import com.gabriellferreira.basecleanviper.domain.util.entity.Page
import com.gabriellferreira.basecleanviper.domain.util.mapper.PageMapper
import com.gabriellferreira.basecleanviper.query.GetPostListQuery
import com.gabriellferreira.basecleanviper.query.GetPostQuery
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.spekframework.spek2.style.specification.describe

@ExperimentalCoroutinesApi
object PostDataRepositoryTest : CoroutineSpek({

    describe("PostDataRepository") {
        val postApi: PostApi = mockk()
        val postMapper: PostMapper = mockk()
        val pageMapper: PageMapper = mockk()
        val repository = PostDataRepository(
            postApi, postMapper, pageMapper
        )

        afterEachTest {
            clearAllMocks()
        }

        context("When fetch Post and no errors") {
            val postId = "abc"
            val result: Post = mockk()
            val response: Response<GetPostQuery.Data> = mockk()
            coEvery { postApi.fetch(postId) } returns flowOf(response)
            coEvery { postMapper.map(any()) } returns result
            coEvery { response.errors } returns null
            coEvery { response.data?.post?.fragments?.post } returns mockk()

            var actualResult: Post? = null
            runBlockingTest {
                actualResult = repository.get(postId).firstOrNull()
            }
            it("It should return result") {
                Assert.assertEquals(result, actualResult)
            }
        }

        context("When fetch Post and errors") {
            val postId = "abc"
            val result: Post = mockk()
            val response: Response<GetPostQuery.Data> = mockk()
            coEvery { postApi.fetch(postId) } returns flowOf(response)
            coEvery { postMapper.map(any()) } returns result
            coEvery { response.errors } returns listOf(Error("TestError"))
            coEvery { response.data?.post?.fragments?.post } returns mockk()

            var actualResult: Post? = null
            runBlockingTest {
                try {
                    actualResult = repository.get(postId).firstOrNull()
                } catch (error: Throwable) {
                    //Skip
                }

            }
            it("It shouldn't return result") {
                Assert.assertEquals(null, actualResult)
            }
        }

        context("When fetch PostList and no errors") {
            val pageNumber : Int = 1
            val pageSize : Int = 10
            val result: Page<Post> = mockk()
            val response: Response<GetPostListQuery.Data> = mockk()
            coEvery { postApi.fetchAll(pageNumber, pageSize) } returns flowOf(response)
            coEvery { pageMapper.map(any()) } returns result
            coEvery { response.errors } returns null
            coEvery { response.data } returns mockk()

            var actualResult: Page<Post>? = null
            runBlockingTest {
                actualResult = repository.getList(pageNumber, pageSize).firstOrNull()
            }
            it("It should return result") {
                Assert.assertEquals(result, actualResult)
            }
        }

        context("When fetch PostList and errors") {
            val pageNumber : Int = 1
            val pageSize : Int = 10
            val result: Page<Post> = mockk()
            val response: Response<GetPostListQuery.Data> = mockk()
            coEvery { postApi.fetchAll(pageNumber, pageSize) } returns flowOf(response)
            coEvery { pageMapper.map(any()) } returns result
            coEvery { response.errors } returns listOf(Error("TestError"))

            var actualResult: Page<Post>? = null
            runBlockingTest {
                try {
                    actualResult = repository.getList(pageNumber, pageSize).firstOrNull()
                } catch (error: Throwable) {
                    //Skip
                }

            }
            it("It shouldn't return result") {
                Assert.assertEquals(null, actualResult)
            }
        }
    }
})