package com.example.presentation.components.host

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.theme.ExperimentsAndroidTheme


@Composable
internal fun App() {
    AppTheme { AppDrawer() }
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