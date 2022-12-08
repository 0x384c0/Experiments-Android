package com.example.domain.model

data class RedditPostsModel(
    val title: String?,
    val subreddit: String?,
    val authorFullName: String?,
    val subredditNamePrefixed: String?,
    val thumbnailHeight: Int?,
    val thumbnailWidth: Int?,
    val ups: Int?,
    val thumbnail: String?,
)