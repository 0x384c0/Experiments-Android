package com.example.domain.model

data class RedditPostsModel(
    val title: String?,
    val author: String?,
    val subredditNamePrefixed: String?,
    val ups: Int?,
    val thumbnail: String?,
)