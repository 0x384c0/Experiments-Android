package com.example.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.composables.Root
import com.example.ui.theme.ExperimentsAndroidTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RootActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ActivityContent() }
    }
}

@Composable
private fun ActivityContent() {
    ExperimentsAndroidTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = { Root() }
        )
    }
}

@Preview
@Composable
fun ComposablePreview() {
    ActivityContent()
}