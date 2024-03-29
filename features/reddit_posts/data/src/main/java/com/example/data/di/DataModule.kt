package com.example.data.di

import com.example.data.repository.RedditRepositoryImpl
import com.example.domain.repository.RedditRepository
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
}