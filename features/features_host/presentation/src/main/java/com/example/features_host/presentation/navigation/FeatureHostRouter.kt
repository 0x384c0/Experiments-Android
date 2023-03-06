package com.example.features_host.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.animations_demo.presentation.navigation.AnimationsDemoRouter
import com.example.features_host.presentation.R
import com.example.presentation.data.DrawerItemState
import com.example.presentation.navigation.RedditPostsRouter


private const val ROUTE_REDDIT_POSTS = "ROUTE_REDDIT_POSTS"
private const val ROUTE_ANIMATIONS = "ROUTE_ANIMATIONS"

class FeatureHostRouter{
    private val redditPostsRouter = RedditPostsRouter()
    private val animationsDemoRouter = AnimationsDemoRouter()

    val builder: NavGraphBuilder.() -> Unit = {
        composable(ROUTE_REDDIT_POSTS) { redditPostsRouter.EntryPoint() }
        composable(ROUTE_ANIMATIONS) { animationsDemoRouter.EntryPoint() }
    }

    val drawerItems = listOf(
        DrawerItemState(
            Icons.Default.Home,
            R.string.drawer_home,
            ROUTE_REDDIT_POSTS,
        ),
        DrawerItemState(
            Icons.Default.PlayArrow,
            R.string.drawer_animations,
            ROUTE_ANIMATIONS,
        )
    )

    val startDestination = ROUTE_REDDIT_POSTS
}