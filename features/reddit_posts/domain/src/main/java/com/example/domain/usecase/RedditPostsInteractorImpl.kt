package com.example.domain.usecase

import com.example.domain.model.RedditPostSortModel
import com.example.domain.model.RedditPostsModel
import com.example.domain.repository.RedditRepository
import javax.inject.Inject

private const val POPULAR_SUBREDDIT = "popular"

internal class RedditPostsInteractorImpl @Inject constructor(
    private val repository: RedditRepository
) : RedditPostsInteractor {
    override suspend fun getTopPosts(): List<RedditPostsModel> {
        return repository.getPosts(
            POPULAR_SUBREDDIT,
            RedditPostSortModel.Hot
        )
    }
}
