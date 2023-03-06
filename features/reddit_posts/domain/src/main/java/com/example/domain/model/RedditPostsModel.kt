package com.example.domain.model

data class RedditPostsModel(
    val permalink: String?,
    val title: String?,
    val author: String?,
    val subredditNamePrefixed: String?,
    val ups: Int?,
    val thumbnail: String?,
)