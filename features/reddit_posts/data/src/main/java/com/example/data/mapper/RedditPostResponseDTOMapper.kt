package com.example.data.mapper

import com.example.data.entity.RedditPostResponseDTO
import com.example.utils.DataMapper
import com.example.domain.model.RedditPostsModel
import javax.inject.Inject

internal class RedditPostResponseDTOMapper @Inject constructor() : DataMapper<RedditPostResponseDTO, List<RedditPostsModel>> {
    override fun map(input: RedditPostResponseDTO): List<RedditPostsModel> = input
        .data.children
        .map {
            RedditPostsModel(
                it.data.title,
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