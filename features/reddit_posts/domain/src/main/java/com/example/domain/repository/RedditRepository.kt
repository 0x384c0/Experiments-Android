package com.example.domain.repository

import com.example.domain.model.RedditPostSortModel
import com.example.domain.model.RedditPostsModel


interface RedditRepository {
    suspend fun getPosts(
        subreddit: String,
        sort: RedditPostSortModel
    ): List<RedditPostsModel>
}