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
                permalink = it.data.permalink,
                title = it.data.title,
                author = it.data.author,
                subredditNamePrefixed = it.data.subredditNamePrefixed,
                ups = it.data.ups,
                thumbnail = it.data.thumbnail,
            )
        }
}