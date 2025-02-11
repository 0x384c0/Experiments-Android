package com.example.presentation.mapper

import android.net.Uri
import com.example.presentation.data.PostItemState
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PostItemStateMapperTest {
    private lateinit var sut: PostItemStateMapper

    private val mockPost = PostItemState(
        permalink = "author",
        author = "category",
        icon = Uri.EMPTY,
        category = "category",
        title = "title",
    )

    @Before
    fun setup() {
        sut = PostItemStateMapper()
    }

    @Test
    fun map() {
        val output = sut.map(mockPost)
        Assert.assertEquals(mockPost.author, output.author)
        Assert.assertEquals(mockPost.category, output.subredditNamePrefixed)
    }
}