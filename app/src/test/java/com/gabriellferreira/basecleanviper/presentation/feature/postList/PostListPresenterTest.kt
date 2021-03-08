package com.gabriellferreira.basecleanviper.presentation.feature.postList

import androidx.paging.PagingData
import com.gabriellferreira.basecleanviper.CoroutineSpek
import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import com.gabriellferreira.basecleanviper.presentation.feature.postList.entity.PostItem
import com.gabriellferreira.basecleanviper.presentation.feature.postList.entity.PostItemMapper
import com.gabriellferreira.basecleanviper.presentation.feature.postList.pagingSource.PostPagingSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.spekframework.spek2.style.specification.describe
import java.lang.Exception

@ExperimentalCoroutinesApi
object PostListPresenterTest : CoroutineSpek({

    describe("PostListPresenter") {
        lateinit var pagingSource: PostPagingSource
        lateinit var presenter: PostListPresenter
        lateinit var view: PostListContract.View
        lateinit var router: PostListContract.Router
        lateinit var mapper: PostItemMapper

        beforeEachTest {
            pagingSource = mockk()
            view = spyk()
            router = spyk()
            mapper = mockk()
            presenter = PostListPresenter(
                pagingSource,
                mapper
            )
            presenter.router = router
            presenter.attachView(view)
        }

        afterEachTest { presenter.detachView() }

        context("When getPostList called") {
            val result: PagingData<Post> = PagingData.from(mockk())

            beforeEachTest {
                coEvery { pagingSource() } returns flowOf(result)
                coEvery { mapper.map(any()) } returns mockk()
                presenter.getPostList()
            }

            it("It should return success") {
                coVerify(exactly = 1) { pagingSource() }
                coVerify(exactly = 1) { view.showItems(any()) }
            }
        }

        context("When getPostList called") {
            val msg = "Generic error"

            beforeEachTest {
                coEvery { pagingSource() } returns flow {
                    throw Exception(msg)
                }
                presenter.getPostList()
            }

            it("It should return error") {
                coVerify(exactly = 1) { view.showToast(msg) }
            }
        }

        context("When onPostItem is clicked"){
            val item: PostItem = mockk()
            val itemId = "abc"

            beforeEachTest {
                coEvery { item.id } returns itemId
                presenter.onPostItemClicked(item)
            }

            it("It should navigate to PostDetails screen"){
                coVerify(exactly = 1) { router.navigateToPostDetails(itemId) }
            }
        }
    }
})