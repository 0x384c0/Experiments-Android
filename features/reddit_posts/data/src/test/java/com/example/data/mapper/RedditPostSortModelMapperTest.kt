package com.example.data.mapper

import com.example.domain.model.RedditPostSortModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class RedditPostSortModelMapperTest {
    private lateinit var sut: RedditPostSortModelMapper

    @Before
    fun setup() {
        sut = RedditPostSortModelMapper()
    }

    @Test
    fun map() {
        Assert.assertEquals("hot", sut.map(RedditPostSortModel.Hot))
        Assert.assertEquals("new", sut.map(RedditPostSortModel.New))
    }
}