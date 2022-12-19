package com.example.data.service

import com.example.data.entity.RedditPostResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface RedditApiService {
    @GET("r/{subreddit}/{sort}.json")
    suspend fun getPosts(
        @Path("subreddit") subreddit: String,
        @Path("sort") sort: String
    ): RedditPostResponseDTO
}