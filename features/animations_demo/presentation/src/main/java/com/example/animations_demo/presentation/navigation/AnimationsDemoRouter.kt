package com.example.animations_demo.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.animations_demo.presentation.component.AnimationsDemoScreen

private const val ROUTE_ANIMATIONS = "ROUTE_ANIMATIONS"

class AnimationsDemoRouter {
    //region router init
    val startDestination = ROUTE_ANIMATIONS

    val builder: NavGraphBuilder.() -> Unit = {
        composable(ROUTE_ANIMATIONS) { AnimationsDemoScreen() }
    }
    //endregion
}