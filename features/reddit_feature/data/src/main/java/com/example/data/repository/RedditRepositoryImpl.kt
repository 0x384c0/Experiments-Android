package com.example.data.repository

import com.example.data.entity.RedditPostResponseDTO
import com.example.data.service.RedditApiService

internal class RedditRepositoryImpl(
    private val api: RedditApiService,
    private val redditPostResponseDTOMapper: DataMapper<RedditPostResponseDTO, List<RedditPostsModel>>,
    private val redditPostSortModelMapper: DataMapper<RedditPostSortModel, String>
) :RedditRepository{
    override suspend fun getPosts(
        subreddit: String,
        sort: RedditPostSortModel
    ): List<RedditPostsModel> {
        val dto = api.getPosts(subreddit, redditPostSortModelMapper.map(sort))
        return redditPostResponseDTOMapper.map(dto)
    }
}

//TODO: to domain
enum class RedditPostSortModel {
    Hot, New
}

data class RedditPostsModel(
    val subreddit: String?,
    val authorFullName: String?,
    val subredditNamePrefixed: String?,
    val thumbnailHeight: Int?,
    val thumbnailWidth: Int?,
    val ups: Int?,
    val thumbnail: String?,
)

interface DataMapper<IN, OUT> {
    fun map(input: IN): OUT
}

private const val DEFAULT_SUBREDDIT = "popular"
interface RedditRepository{
    suspend fun getPosts(
        subreddit: String = DEFAULT_SUBREDDIT,
        sort: RedditPostSortModel = RedditPostSortModel.Hot
    ): List<RedditPostsModel>
}