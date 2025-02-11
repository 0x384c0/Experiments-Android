package com.example.presentation.mapper

import com.example.domain.model.RedditPostsModel
import com.example.presentation.data.HomeUiState
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class RedditPostsModelsMapperTest {
    private lateinit var sut: RedditPostsModelsMapper

    private val mockPost = RedditPostsModel(
        permalink = "title",
        title = "author",
        author = "subredditNamePrefixed",
        ups = 5,
        subredditNamePrefixed = "subredditNamePrefixed",
        thumbnail = "thumbnail",
    )
    private val mockPosts = (0..5).map { mockPost }

    @Before
    fun setup() {
        sut = RedditPostsModelsMapper()
    }

    @Test
    fun map() {
        val output = sut.map(mockPosts)
        Assert.assertEquals(mockPosts.count(), (output as? HomeUiState.HasPosts)?.posts?.count())
        Assert.assertEquals(mockPosts.first().title, (output as? HomeUiState.HasPosts)?.posts?.first()?.title)
    }

    @Test
    fun mapEmpty() {
        val output = sut.map(listOf())
        assert(output is HomeUiState.NoPosts)
    }
}