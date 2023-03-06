package com.example.presentation.mapper

import android.net.Uri
import com.example.domain.model.RedditPostsModel
import com.example.presentation.data.HomeUiState
import com.example.presentation.data.PostItemState
import com.example.utils.DataMapper
import javax.inject.Inject

internal class RedditPostsModelsMapper @Inject constructor() : DataMapper<List<RedditPostsModel>, HomeUiState> {
    override fun map(input: List<RedditPostsModel>): HomeUiState {
        val posts = input.map { it ->
            PostItemState(
                permalink = it.permalink ?: "",
                author = it.author ?: "",
                category = it.subredditNamePrefixed ?: "",
                icon = it.thumbnail?.let { Uri.parse(it) } ?: Uri.EMPTY,
                title = it.title ?: "",
            )
        }
        return if (posts.isEmpty()) {
            HomeUiState.NoPosts(
                isLoading = false,
                errorMessages = emptyList(),
                searchInput = ""
            )
        } else {
            HomeUiState.HasPosts(
                posts = posts,
                isLoading = false,
                errorMessages = emptyList(),
                searchInput = ""
            )
        }
    }
}