package com.example.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.presentation.components.screens.HomeScreen
import com.example.presentation.components.screens.PostDetails
import java.net.URLEncoder
import javax.inject.Inject

private const val ARGUMENT = "argument"
private const val POST_DETAILS = "POST_DETAILS/{$ARGUMENT}"
private const val ROUTE_REDDIT_POSTS = "ROUTE_REDDIT_POSTS"

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
        composable(POST_DETAILS) { PostDetails(permalink = it.arguments?.getString(ARGUMENT)) }
        composable(ROUTE_REDDIT_POSTS) { HomeScreen() }
    }
    //endregion

    //region routes
    override fun postDetails(permalink: String) {
        navHostController.navigate(POST_DETAILS.replace("{$ARGUMENT}", URLEncoder.encode(permalink, "utf-8")))
    }
    //endregion
}

internal class MockRedditPostsRouterImpl : RedditPostsRouter {
    override val startDestination = ROUTE_REDDIT_POSTS
    override val builder: NavGraphBuilder.() -> Unit = {}
    override fun postDetails(permalink: String) {}
}