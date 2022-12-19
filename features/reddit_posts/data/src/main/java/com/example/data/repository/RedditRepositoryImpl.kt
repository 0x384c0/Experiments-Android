package com.example.data.repository

import com.example.data.entity.RedditPostResponseDTO
import com.example.data.service.RedditApiService
import com.example.domain.model.RedditPostSortModel
import com.example.domain.model.RedditPostsModel
import com.example.domain.repository.RedditRepository
import com.example.utils.DataMapper
import javax.inject.Inject

internal class RedditRepositoryImpl @Inject constructor(
    private val api: RedditApiService,
    private val redditPostResponseDTOMapper: DataMapper<RedditPostResponseDTO, List<RedditPostsModel>>,
    private val redditPostSortModelMapper: DataMapper<RedditPostSortModel, String>
) : RedditRepository {
    override suspend fun getPosts(
        subreddit: String,
        sort: RedditPostSortModel
    ): List<RedditPostsModel> {
        val dto = api.getPosts(subreddit, redditPostSortModelMapper.map(sort))
        return redditPostResponseDTOMapper.map(dto)
    }
}