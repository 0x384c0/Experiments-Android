package com.example.data.mapper

import com.example.data.repository.DataMapper
import com.example.data.repository.RedditPostSortModel

internal class RedditPostSortModelMapper : DataMapper<RedditPostSortModel, String> {
    override fun map(input: RedditPostSortModel): String = when (input) {
        RedditPostSortModel.Hot -> "hot"
        RedditPostSortModel.New -> "new"
    }
}