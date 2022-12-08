package com.example.domain.di

import com.example.domain.usecase.RedditPostsInteractor
import com.example.domain.usecase.RedditPostsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DomainModule {
    @Binds
    abstract fun provideRedditPostsInteractor(impl: RedditPostsInteractorImpl): RedditPostsInteractor
}