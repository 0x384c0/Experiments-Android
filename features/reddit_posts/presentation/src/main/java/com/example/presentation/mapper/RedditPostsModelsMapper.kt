package com.example.presentation.mapper

import android.net.Uri
import com.example.domain.model.RedditPostsModel
import com.example.presentation.R
import com.example.presentation.data.HomeUiState
import com.example.presentation.data.PostItemState
import com.example.utils.DataMapper
import javax.inject.Inject

internal class RedditPostsModelsMapper @Inject constructor() : DataMapper<List<RedditPostsModel>, HomeUiState> {
    override fun map(input: List<RedditPostsModel>): HomeUiState {
        val posts = input.map {
            PostItemState(
                author = it.author ?: "",
                category = it.subredditNamePrefixed ?: "",
                icon = Uri.parse(it.thumbnail),
                title = it.title ?: "",
            )
        }
        return HomeUiState.HasPosts(
            posts = posts,
            isLoading = false,
            errorMessages = emptyList(),
            searchInput = ""
        )
    }
}