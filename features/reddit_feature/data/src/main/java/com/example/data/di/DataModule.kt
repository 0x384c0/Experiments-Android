package com.example.data.di

import com.example.data.repository.RedditRepository
import com.example.data.repository.RedditRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun bindRedditRepositoryImpl(
        impl: RedditRepositoryImpl
    ): RedditRepository

//    @Binds
//    abstract fun bindRedditPostResponseDTOMapper(
//        impl: RedditPostResponseDTOMapper
//    ): DataMapper<RedditPostResponseDTO, List<RedditPostsModel>>
//
//    @Binds
//    abstract fun bindRedditPostSortModelMapper(
//        impl: RedditPostSortModelMapper
//    ): DataMapper<RedditPostSortModel, String>
}

