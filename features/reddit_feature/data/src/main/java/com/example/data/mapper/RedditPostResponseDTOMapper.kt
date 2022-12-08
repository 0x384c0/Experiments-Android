package com.example.data.mapper

import com.example.data.entity.RedditPostResponseDTO
import com.example.data.repository.DataMapper
import com.example.data.repository.RedditPostsModel

internal class RedditPostResponseDTOMapper : DataMapper<RedditPostResponseDTO, List<RedditPostsModel>> {
    override fun map(input: RedditPostResponseDTO): List<RedditPostsModel> = input
        .data.children
        .map {
            RedditPostsModel(
                it.data.subreddit,
                it.data.authorFullName,
                it.data.subredditNamePrefixed,
                it.data.thumbnailHeight,
                it.data.thumbnailWidth,
                it.data.ups,
                it.data.thumbnail,
            )
        }
}