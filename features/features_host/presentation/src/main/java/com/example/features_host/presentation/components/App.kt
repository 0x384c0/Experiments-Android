package com.example.features_host.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.features_host.presentation.navigation.FeatureHostRouter
import com.example.features_host.presentation.theme.ExperimentsAndroidTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController,
    router: FeatureHostRouter,
) {
    AppTheme {
        AppDrawer(
            coroutineScope = coroutineScope,
            drawerItems = router.drawerItems,
            onItemClick = { router.navigate(it) },
        ) { name, drawerState ->
            AppScaffold(
                title = name,
                canGoBack = router.canGoBack(),
                backClick = { router.back() },
                topBarClick = { coroutineScope.launch { drawerState.open() } },
            ) { padding ->
                NavHost(
                    modifier = Modifier.padding(padding),
                    navController = navController,
                    startDestination = router.startDestination,
                    builder = router.builder
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


