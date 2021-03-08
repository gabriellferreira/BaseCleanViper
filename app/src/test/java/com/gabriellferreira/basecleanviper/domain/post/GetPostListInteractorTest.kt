package com.gabriellferreira.basecleanviper.domain.post

import com.gabriellferreira.basecleanviper.CoroutineSpek
import com.gabriellferreira.basecleanviper.domain.post.GetPostListInteractor.Companion.POST_LIST_PAGE_SIZE
import com.gabriellferreira.basecleanviper.domain.post.entity.Post
import com.gabriellferreira.basecleanviper.domain.util.entity.Page
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.spekframework.spek2.style.specification.describe

@ExperimentalCoroutinesApi
object GetPostListInteractorTest : CoroutineSpek({

    describe("GetPostListInteractor") {
        lateinit var postRepository: PostRepository
        lateinit var dispatcher: CoroutineDispatcher
        lateinit var interactor: GetPostListInteractor

        beforeEachTest {
            postRepository = mockk()
            dispatcher = TestCoroutineDispatcher()
            interactor = GetPostListInteractor(postRepository, dispatcher)
        }

        context("When use case is called") {
            val pageNumber = 10
            val result: Page<Post> = mockk()
            var actual: Page<Post>? = null

            beforeEachTest {
                coEvery { postRepository.getList(any(), any()) } returns flowOf(result)
                runBlockingTest {
                    actual = interactor(pageNumber).first()
                }
            }

            it("It should return proper list") {
                coVerify(exactly = 1) { postRepository.getList(pageNumber, POST_LIST_PAGE_SIZE) }
                Assert.assertEquals(actual, result)
            }
        }
    }
})