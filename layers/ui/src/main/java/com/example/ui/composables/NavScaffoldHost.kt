package com.example.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavScaffoldHost(
    navController: NavHostController,
    title: String,
    startDestination:String,
    topBarClick: () -> Unit,
    builder: NavGraphBuilder.() -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(title) }, navigationIcon = {
            IconButton(onClick = { topBarClick() }) {
                Icon(Icons.Filled.Menu, contentDescription = "Localized description")
            }
        })
    }) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = startDestination,
            builder = builder
        )
    }
}