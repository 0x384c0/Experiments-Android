package com.example.presentation.mock

import com.example.domain.model.RedditPostsModel
import com.example.domain.usecase.RedditPostsInteractor


internal class MockRedditPostsInteractor : RedditPostsInteractor {
    companion object {
        internal val mockPostModel = RedditPostsModel(
            title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            author = "AuthorName",
            subredditNamePrefixed = "r/subreddit",
            ups = 12,
            thumbnail = null,
        )
    }

    override suspend fun getTopPosts(): List<RedditPostsModel> {
        return (1..10).map { mockPostModel }
    }
}