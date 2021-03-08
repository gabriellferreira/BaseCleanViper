package com.gabriellferreira.basecleanviper.presentation.feature.postDetails

import com.gabriellferreira.basecleanviper.CoroutineSpek
import com.gabriellferreira.basecleanviper.domain.post.GetPostInteractor
import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import com.gabriellferreira.basecleanviper.presentation.feature.postDetails.entity.PostDetailsViewItemMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.spekframework.spek2.style.specification.describe

object PostDetailsPresenterTest : CoroutineSpek({

    val postId = "abcd"

    describe("PostDetailsPresenter") {

        lateinit var interactor: GetPostInteractor
        lateinit var presenter: PostDetailsPresenter
        lateinit var view: PostDetailsContract.View
        lateinit var router: PostDetailsContract.Router
        lateinit var mapper: PostDetailsViewItemMapper

        beforeEachTest {
            interactor = mockk()
            view = spyk()
            router = spyk()
            mapper = mockk()
            presenter = PostDetailsPresenter(
                interactor,
                mapper
            )
            presenter.router = router
            presenter.attachView(view)
        }

        afterEachTest { presenter.detachView() }

        context("When fetch Post Details is called") {
            val result: Post = mockk()

            beforeEachTest {
                coEvery { interactor(postId) } returns flowOf(result)
                coEvery { mapper.map(any()) } returns mockk()
                presenter.fetch(postId)
            }

            it("It should return success") {
                coVerify(exactly = 1) { view.showProgress() }
                coVerify(exactly = 1) { view.hideProgress() }
                coVerify(exactly = 1) { view.setupView(any()) }
            }
        }

        context("When fetch Post Details is called") {
            val msg = "Generic error"

            beforeEachTest {
                coEvery { interactor(postId) } returns flow {
                    throw Exception(msg)
                }
                presenter.fetch(postId)
            }

            it("It should return error") {
                coVerify(exactly = 1) { view.hideProgress() }
                coVerify(exactly = 1) { view.showToast(msg) }
            }
        }
    }
})