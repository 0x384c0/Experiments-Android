package com.example.domain.usecase

import com.example.domain.model.RedditPostsModel

interface RedditPostsInteractor {
    suspend fun getTopPosts(): List<RedditPostsModel>
}