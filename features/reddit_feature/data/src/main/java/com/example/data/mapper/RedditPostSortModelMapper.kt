package com.example.data.mapper

import com.example.utils.DataMapper
import com.example.domain.model.RedditPostSortModel
import javax.inject.Inject

internal class RedditPostSortModelMapper @Inject constructor() : DataMapper<RedditPostSortModel, String> {
    override fun map(input: RedditPostSortModel): String = when (input) {
        RedditPostSortModel.Hot -> "hot"
        RedditPostSortModel.New -> "new"
    }
}