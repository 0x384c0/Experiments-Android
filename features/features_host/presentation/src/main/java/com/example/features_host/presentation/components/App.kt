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
import com.example.animations_demo.presentation.navigation.AnimationsDemoNavigation
import com.example.features_host.presentation.R
import com.example.features_host.presentation.theme.ExperimentsAndroidTheme
import com.example.presentation.data.DrawerItemState
import com.example.presentation.navigation.RedditPostsNavigation
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
    val animationsDemoNavigation = remember { AnimationsDemoNavigation() }
    AppTheme {
        AppDrawer(
            coroutineScope = coroutineScope,
            drawerItems = listOf(
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
                        composable(ROUTE_REDDIT_POSTS) { redditPostsNavigation.EntryPoint() }
                        composable(ROUTE_ANIMATIONS) { animationsDemoNavigation.EntryPoint() }
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


