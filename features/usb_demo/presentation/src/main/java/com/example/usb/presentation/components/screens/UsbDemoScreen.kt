package com.example.usb.presentation.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.usb.presentation.components.widgets.UsbDevicesListView

@Composable
internal fun UsbDemoScreen() {
    Column {
        Text(text = "USB Demo")
        UsbDevicesListView()
    }
}