package com.example.presentation.mapper

import com.example.domain.model.RedditPostsModel
import com.example.presentation.data.PostItemState
import com.example.utils.DataMapper
import javax.inject.Inject

internal class PostItemStateMapper @Inject constructor() : DataMapper<PostItemState, RedditPostsModel> {
    override fun map(input: PostItemState): RedditPostsModel = RedditPostsModel(
        title = input.title,
        author = input.author,
        subredditNamePrefixed = input.category,
        ups = null,
        thumbnail = input.icon.path,
    )
}