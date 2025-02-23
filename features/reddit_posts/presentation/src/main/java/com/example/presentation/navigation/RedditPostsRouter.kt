package com.example.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.presentation.components.screens.HomeScreen
import com.example.presentation.components.screens.PostDetails
import java.net.URLEncoder
import javax.inject.Inject

private const val PERMALINK = "permalink"
private const val POST_DETAILS = "post_details"
private const val ROUTE_REDDIT_POSTS = "route_reddit_posts"

interface RedditPostsRouter {
    val startDestination: String
    val builder: NavGraphBuilder.() -> Unit
    fun postDetails(permalink: String)
}

internal class RedditPostsRouterImpl @Inject constructor(
    private val navHostController: NavHostController
) : RedditPostsRouter {

    //region router init
    override val startDestination = ROUTE_REDDIT_POSTS

    override val builder: NavGraphBuilder.() -> Unit = {
        composable(ROUTE_REDDIT_POSTS) { HomeScreen() }
        composable(
            "$POST_DETAILS/{$PERMALINK}",
            arguments = listOf(
                navArgument(PERMALINK) { type = NavType.StringType },
            ),
        ) { PostDetails(permalink = it.arguments?.getString(PERMALINK)) }
    }
    //endregion

    //region routes
    override fun postDetails(permalink: String) {
        navHostController.navigate("$POST_DETAILS/${URLEncoder.encode(permalink, "utf-8")}")
    }
    //endregion
}

internal class MockRedditPostsRouterImpl : RedditPostsRouter {
    override val startDestination = ROUTE_REDDIT_POSTS
    override val builder: NavGraphBuilder.() -> Unit = {}
    override fun postDetails(permalink: String) {}
}