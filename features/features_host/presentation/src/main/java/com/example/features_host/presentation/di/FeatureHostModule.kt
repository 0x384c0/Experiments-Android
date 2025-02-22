package com.example.features_host.presentation.di

import android.annotation.SuppressLint
import androidx.navigation.NavHostController
import com.example.features_host.presentation.navigation.FeatureHostRouter
import com.example.presentation.navigation.RedditPostsRouter
import com.example.usb.presentation.navigation.UsbDemoRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class FeatureHostModule {
    internal companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var navHostController: NavHostController
        fun setNavController(navHostController: NavHostController) {
            this.navHostController = navHostController
        }
    }

    @Provides
    fun provideFeatureHostRouter(
        redditPostsRouter: RedditPostsRouter,
        navHostController: NavHostController,
        usbDemoRouter: UsbDemoRouter,
    ): FeatureHostRouter = FeatureHostRouter(
        redditPostsRouter,
        navHostController,
        usbDemoRouter,
    )

    @Provides
    fun provideNavHostController(): NavHostController = navHostController
}