package com.example.usb.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.usb.presentation.components.screens.UsbDemoScreen
import com.example.usb.presentation.components.screens.UsbDetailsScreen
import java.net.URLEncoder
import javax.inject.Inject

private const val ROUTE_USB_DEMO = "route_usb_demo"
private const val USB_DEVICE_NAME = "usb_device_name"
private const val USB_DETAILS = "usb_details"

interface UsbDemoRouter {
    val startDestination: String
    val builder: NavGraphBuilder.() -> Unit

    fun usbDetails(deviceName: String)
}

internal class UsbDemoRouterImpl @Inject constructor(
    private val navHostController: NavHostController
) : UsbDemoRouter {

    //region router init
    override val startDestination = ROUTE_USB_DEMO

    override val builder: NavGraphBuilder.() -> Unit = {
        composable(ROUTE_USB_DEMO) { UsbDemoScreen() }
        composable(
            "$USB_DETAILS/{$USB_DEVICE_NAME}",
            arguments = listOf(
                navArgument(USB_DEVICE_NAME) { type = NavType.StringType },
            ),
        ) { UsbDetailsScreen(deviceName = it.arguments?.getString(USB_DEVICE_NAME)) }
    }
    //endregion

    //region routes
    override fun usbDetails(deviceName: String) {
        navHostController.navigate("$USB_DETAILS/${URLEncoder.encode(deviceName, "utf-8")}")
    }
    //endregion
}

internal class MockUsbDemoRouterImpl : UsbDemoRouter {
    override val startDestination = ROUTE_USB_DEMO
    override val builder: NavGraphBuilder.() -> Unit = {}
    override fun usbDetails(deviceName: String) {}
}