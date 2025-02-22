package com.example.features_host.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppScaffold(
    title: String,
    canGoBack: Boolean,
    backClick: () -> Unit,
    topBarClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    if (canGoBack){
                        IconButton(onClick = { backClick() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "")
                        }
                    } else {
                        IconButton(onClick = { topBarClick() }) {
                            Icon(Icons.Filled.Menu, contentDescription = "")
                        }
                    }
                }
            )
        },
        content = content
    )
}

@Preview
@Composable
private fun ComposablePreviewDrawer() {
    AppScaffold(
        title = "Test title",
        canGoBack = false,
        backClick = {},
        topBarClick = {},
    ) { Text(text = "Test content") }
}

@Preview
@Composable
private fun ComposablePreviewBack() {
    AppScaffold(
        title = "Test title",
        canGoBack = true,
        backClick = {},
        topBarClick = {},
    ) { Text(text = "Test content") }
}