package com.example.data.mapper

import com.example.data.entity.RedditPostDTO
import com.example.data.entity.RedditPostDataDTO
import com.example.data.entity.RedditPostDataResponseDTO
import com.example.data.entity.RedditPostResponseDTO
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RedditPostResponseDTOMapperTest {
    private lateinit var sut: RedditPostResponseDTOMapper

    private val mockPost = RedditPostDataDTO(
        "title",
        "subreddit",
        "author",
        "subredditNamePrefixed",
        12,
        12,
        3,
        "thumbnail",
    )
    private val mockPosts = (0..5).map { RedditPostDTO(mockPost) }

    @Before
    fun setup() {
        sut = RedditPostResponseDTOMapper()
    }

    @Test
    fun map() {
        val input = RedditPostResponseDTO(RedditPostDataResponseDTO(mockPosts))
        val output = sut.map(input)
        Assert.assertEquals(mockPosts.count(), output.count())
        Assert.assertEquals(mockPosts.first().data.title, output.first().title)
    }
}
