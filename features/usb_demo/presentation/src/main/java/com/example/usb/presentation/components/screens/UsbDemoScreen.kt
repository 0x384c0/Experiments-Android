package com.example.usb.presentation.components.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.usb.presentation.components.widgets.UsbDevicesListView
import com.example.usb.presentation.data.UsbDeviceState
import com.example.utils.ScaffoldPreview

@Composable
internal fun UsbDemoScreen() {
    UsbDevicesListView(onDeviceClick = ::onDeviceClick)
}

private fun onDeviceClick(usbDevice: UsbDeviceState) {

}

@Preview
@Composable
internal fun UsbDemoScreenPreview() {
    ScaffoldPreview("USB Demo") { UsbDemoScreen() }
}
