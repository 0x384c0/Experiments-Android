package com.example.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val ROUTE_HOME = "home"
private const val ROUTE_ANIMATIONS = "animations_demo"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Root(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
) {
    NavDrawer(
        drawerItems = listOf(
            ROUTE_HOME to Icons.Default.Home,
            ROUTE_ANIMATIONS to Icons.Default.PlayArrow
        ),
        coroutineScope = coroutineScope,
        navController = navController
    ) { name, drawerState ->
        NavScaffoldHost(
            navController = navController,
            title = name,
            startDestination = ROUTE_HOME,
            topBarClick = { coroutineScope.launch { drawerState.open() } },
            builder = {
                //TODO: nav routes to separate class
                composable(ROUTE_HOME) { Home() }
                composable(ROUTE_ANIMATIONS) { AnimationsDemo() }
            }
        )
    }
}