package com.example.presentation.di

import com.example.presentation.navigation.RedditPostsRouter
import com.example.presentation.navigation.RedditPostsRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal abstract class PresentationModule {
    @Binds
    abstract fun provideRedditPostsInteractor(impl: RedditPostsRouterImpl): RedditPostsRouter
}