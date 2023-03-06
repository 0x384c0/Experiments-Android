package com.example.features_host.presentation.di

import androidx.navigation.NavHostController
import com.example.features_host.presentation.navigation.FeatureHostRouter
import com.example.presentation.navigation.RedditPostsRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class FeatureHostModule {
    internal companion object {
        private lateinit var navHostController: NavHostController
        fun setNavController(navHostController: NavHostController) {
            this.navHostController = navHostController
        }
    }

    @Provides
    fun provideFeatureHostRouter(
        redditPostsRouter: RedditPostsRouter,
        navHostController: NavHostController,
    ): FeatureHostRouter = FeatureHostRouter(
        redditPostsRouter,
        navHostController,
    )

    @Provides
    fun provideNavHostController(): NavHostController = navHostController
}