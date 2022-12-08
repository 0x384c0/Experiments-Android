package com.example.data.di

import com.example.data.entity.RedditPostResponseDTO
import com.example.data.mapper.RedditPostResponseDTOMapper
import com.example.data.mapper.RedditPostSortModelMapper
import com.example.domain.model.RedditPostSortModel
import com.example.domain.model.RedditPostsModel
import com.example.utils.DataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class MappersModule {
    @Provides
    fun provideRedditPostResponseDTOMapper(
        impl: RedditPostResponseDTOMapper
    ): DataMapper<RedditPostResponseDTO, List<RedditPostsModel>> = impl

    @Provides
    fun provideRedditPostSortModelMapper(
        impl: RedditPostSortModelMapper
    ): DataMapper<RedditPostSortModel, String> = impl
}