package com.example.features_host.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppScaffold(
    title: String,
    topBarClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) }, navigationIcon = {
                IconButton(onClick = { topBarClick() }) {
                    Icon(Icons.Filled.Menu, contentDescription = "")
                }
            })
        },
        content = content
    )
}

@Preview
@Composable
private fun ComposablePreview() {
    AppScaffold(
        title = "Test title",
        topBarClick = {},
        content = { Text(text = "Test content") },
    )
}