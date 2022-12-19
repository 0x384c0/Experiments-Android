package com.example.features_host.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.features_host.presentation.theme.ExperimentsAndroidTheme
import com.example.presentation.components.screen.AnimationsDemoScreen
import com.example.presentation.components.screen.RedditPostsNavigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


private const val ROUTE_REDDIT_POSTS = "ROUTE_REDDIT_POSTS"
private const val ROUTE_ANIMATIONS = "ROUTE_ANIMATIONS"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
) {
    val redditPostsNavigation = remember { RedditPostsNavigation() }
    AppTheme {
        AppDrawer(
            coroutineScope = coroutineScope,
            drawerItems = listOf(
                ROUTE_REDDIT_POSTS to Icons.Default.Home,
                ROUTE_ANIMATIONS to Icons.Default.PlayArrow
            ),
            onItemClick = { navController.navigate(it) { launchSingleTop = true } },
        ) { name, drawerState ->
            AppScaffold(
                title = name,
                topBarClick = { coroutineScope.launch { drawerState.open() } },
            ) { padding ->
                NavHost(
                    modifier = Modifier.padding(padding),
                    navController = navController,
                    startDestination = ROUTE_REDDIT_POSTS,
                    builder = { //TODO: nav graph in to separate class
                        composable(ROUTE_REDDIT_POSTS) { redditPostsNavigation.entryPoint() }
                        composable(ROUTE_ANIMATIONS) { AnimationsDemoScreen() }
                    }
                )
            }
        }
    }
}

@Composable
internal fun AppTheme(content: @Composable () -> Unit) {
    ExperimentsAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = { content() }
        )
    }
}

