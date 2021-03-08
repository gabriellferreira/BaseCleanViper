package com.gabriellferreira.basecleanviper.domain.post

import com.gabriellferreira.basecleanviper.CoroutineSpek
import com.gabriellferreira.basecleanviper.domain.post.entity.Post
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
object GetPostInteractorTest : CoroutineSpek({

    val pageId = "abc"

    describe("GetPostInteractor") {
        lateinit var postRepository: PostRepository
        lateinit var dispatcher: CoroutineDispatcher
        lateinit var interactor: GetPostInteractor

        beforeEachTest {
            postRepository = mockk()
            dispatcher = TestCoroutineDispatcher()
            interactor = GetPostInteractor(postRepository, dispatcher)
        }

        context("When use case is called") {
            val result: Post = mockk()
            var actual: Post? = null

            beforeEachTest {
                coEvery { postRepository.get(any()) } returns flowOf(result)
                runBlockingTest {
                    actual = interactor(pageId).first()
                }
            }

            it("It should return proper list") {
                coVerify(exactly = 1) {
                    postRepository.get(pageId)
                }
                Assert.assertEquals(actual, result)
            }
        }
    }
})