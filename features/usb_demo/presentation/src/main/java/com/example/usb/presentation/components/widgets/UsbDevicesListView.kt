package com.example.usb.presentation.components.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.usb.presentation.components.items.USBDeviceItem
import com.example.usb.presentation.data.UsbDeviceState
import com.example.usb.presentation.service.UsbServiceRepositoryMockImpl
import com.example.utils.hiltViewModelFactory

@Composable
internal fun UsbDevicesListView(
    vm: UsbDevicesListViewModel = hiltViewModelFactory(::mockVMFactory),
    onDeviceClick: (UsbDeviceState) -> Unit,
) {
    val usbDevices = vm.usbDevices.collectAsState().value


    when {
        usbDevices == null -> {
            Text(text = "Loading USB devices...")
        }

        usbDevices.isEmpty() -> {
            Text(text = "No USB devices connected")
        }

        else -> {
            LazyColumn(
                modifier = Modifier.testTag("items"),
            ) {
                items(usbDevices) { device ->
                    USBDeviceItem(state = device, onClick = { onDeviceClick(device) })
                }
            }
        }
    }
}

private fun mockVMFactory() = UsbDevicesListViewModel(usbServiceRepository = UsbServiceRepositoryMockImpl())

@Preview
@Composable
private fun ComposablePreview() {
    UsbDevicesListView(onDeviceClick = {})
}