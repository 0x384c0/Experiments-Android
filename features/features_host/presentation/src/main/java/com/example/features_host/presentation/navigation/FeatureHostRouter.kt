package com.example.features_host.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.animations_demo.presentation.navigation.AnimationsDemoRouter
import com.example.features_host.presentation.R
import com.example.presentation.data.DrawerItemState
import com.example.presentation.navigation.RedditPostsRouter
import com.example.usb.presentation.navigation.UsbDemoRouter
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


class FeatureHostRouter @Inject constructor(
    private val redditPostsRouter: RedditPostsRouter,
    private val navHostController: NavHostController,
    usbDemoRouter: UsbDemoRouter,
) {
    private val animationsDemoRouter = AnimationsDemoRouter()

    val builder: NavGraphBuilder.() -> Unit = {
        animationsDemoRouter.builder(this)
        redditPostsRouter.builder(this)
        usbDemoRouter.builder(this)
    }

    val drawerItems = listOf(
        DrawerItemState(
            Icons.Default.Home,
            R.string.drawer_home,
            redditPostsRouter.startDestination,
        ),
        DrawerItemState(
            Icons.Default.PlayArrow,
            R.string.drawer_animations,
            animationsDemoRouter.startDestination,
        ),
        DrawerItemState(
            Icons.Default.PlayArrow,
            R.string.usb_demo,
            usbDemoRouter.startDestination,
        )
    )

    val startDestination = redditPostsRouter.startDestination

    @Composable
    fun canGoBack(): Boolean {
        val state by navHostController.currentBackStackEntryAsState()
        return drawerItems.map { it.route }.contains(state?.destination?.route).not()
    }

    fun navigate(drawerItem: DrawerItemState) {
        navHostController.navigate(drawerItem.route) { launchSingleTop = true }
    }

    fun back() {
        navHostController.popBackStack()


    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface Factory {
        fun createRouter(): FeatureHostRouter
    }
}