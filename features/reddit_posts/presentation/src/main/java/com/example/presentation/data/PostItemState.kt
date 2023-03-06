package com.example.presentation.data

import android.net.Uri

internal data class PostItemState(
    val permalink: String,
    val author: String,
    val category: String,
    val icon: Uri,
    val title: String,
)