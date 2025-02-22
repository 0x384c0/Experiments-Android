package com.example.usb.presentation.components.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun UsbDevicesListView(
    vm: UsbDevicesListViewModel = hiltViewModel()
) {
    val usbDevices = vm.usbDevices.collectAsState().value

    Column {
        when {
            usbDevices == null -> {
                Text(text = "Loading USB devices...")
            }

            usbDevices.isEmpty() -> {
                Text(text = "No USB devices connected")
            }

            else -> {
                usbDevices.forEach { device ->
                    Text(text = "Device: ${device.deviceName}")
                }
            }
        }
    }
}