package com.example.presentation.components.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun PostDetails(
    permalink: String?,
) {
    Text(text = permalink.toString())
}