package com.example.presentation.data

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItemState(
    val icon: ImageVector,
    @StringRes val label: Int,
    val route: String,
)