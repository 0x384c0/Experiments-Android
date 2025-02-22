package com.example.usb.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.usb.presentation.components.screens.UsbDemoScreen
import javax.inject.Inject

private const val ROUTE_USB_DEMO = "ROUTE_USB_DEMO"

interface UsbDemoRouter {
    val startDestination: String
    val builder: NavGraphBuilder.() -> Unit
}

internal class UsbDemoRouterImpl @Inject constructor(
) : UsbDemoRouter {

    //region router init
    override val startDestination = ROUTE_USB_DEMO

    override val builder: NavGraphBuilder.() -> Unit = {
        composable(ROUTE_USB_DEMO) { UsbDemoScreen() }
    }
    //endregion
}