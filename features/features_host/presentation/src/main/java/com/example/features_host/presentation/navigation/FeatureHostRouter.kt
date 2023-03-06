package com.example.features_host.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.animations_demo.presentation.navigation.AnimationsDemoRouter
import com.example.features_host.presentation.R
import com.example.presentation.data.DrawerItemState
import com.example.presentation.navigation.RedditPostsRouter
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


class FeatureHostRouter @Inject constructor (
    private val redditPostsRouter: RedditPostsRouter,
    private val navHostController: NavHostController,
) {
    private val animationsDemoRouter = AnimationsDemoRouter()

    val builder: NavGraphBuilder.() -> Unit = {
        animationsDemoRouter.builder(this)
        redditPostsRouter.builder(this)
    }

    val drawerItems = listOf(
        DrawerItemState(
            Icons.Default.Home,
            R.string.drawer_home,
            redditPostsRouter.startDestination,
        ),
        DrawerItemState(
            Icons.Default.PlayArrow,
            R.string.drawer_animations,
            animationsDemoRouter.startDestination,
        )
    )

    val startDestination = redditPostsRouter.startDestination

    fun navigate(drawerItem: DrawerItemState) {
        navHostController.navigate(drawerItem.route) { launchSingleTop = true }
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface Factory {
        fun createRouter() : FeatureHostRouter
    }
}