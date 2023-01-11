package com.example.domain.mock

import com.example.domain.model.RedditPostSortModel
import com.example.domain.model.RedditPostsModel
import com.example.domain.usecase.RedditPostsInteractor


class MockRedditPostsInteractor : RedditPostsInteractor {
    var isEmpty = false

    private val repository = MockRedditRepository()

    override suspend fun getTopPosts(): List<RedditPostsModel> = if (isEmpty) listOf() else repository.getPosts("", RedditPostSortModel.Hot)
}