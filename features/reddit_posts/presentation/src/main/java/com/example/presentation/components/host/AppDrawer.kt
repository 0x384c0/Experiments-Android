package com.example.presentation.components.host

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.components.NavDrawer
import com.example.presentation.components.screen.AnimationsDemoScreen
import com.example.presentation.components.screen.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val ROUTE_HOME = "home"
private const val ROUTE_ANIMATIONS = "animations_demo"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppDrawer(
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
                composable(ROUTE_HOME) { HomeScreen() }
                composable(ROUTE_ANIMATIONS) { AnimationsDemoScreen() }
            }
        )
    }
}