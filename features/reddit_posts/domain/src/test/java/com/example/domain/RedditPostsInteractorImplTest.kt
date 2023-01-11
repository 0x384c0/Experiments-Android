package com.example.domain

import com.example.domain.mock.MockRedditRepository
import com.example.domain.usecase.RedditPostsInteractorImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class RedditPostsInteractorImplTest {
    private lateinit var sut: RedditPostsInteractorImpl

    @Before
    fun setUp() {
        sut = RedditPostsInteractorImpl(MockRedditRepository())
    }

    @Test
    fun `get top posts successful`() = runBlocking {
        val output = sut.getTopPosts()
        Assert.assertEquals(10, output.count())
        Assert.assertEquals("AuthorName", output.first().author)
    }
}