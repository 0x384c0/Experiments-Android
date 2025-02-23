package com.example.usb.presentation.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.usb.presentation.R
import com.example.usb.presentation.components.widgets.USBDeviceItem
import com.example.utils.ScaffoldPreview
import com.example.utils.hiltViewModelWithPreview

@Composable
internal fun UsbDetailsScreen(
    deviceName: String?,
    vm: UsbDetailsScreenViewModel = hiltViewModelWithPreview<UsbDetailsScreenViewModel, UsbDetailsScreenViewModel.Factory>(
        UsbDetailsScreenViewModel.mock(deviceName),
    ) { factory -> factory.create(deviceName) }
) {

    if (deviceName != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LaunchedEffect(deviceName) {
                vm.readData()
            }
            val usbDevice = vm.usbDevice.collectAsState(null).value
            usbDevice?.let {
                USBDeviceItem(it)
            }

            Spacer(modifier = Modifier.size(16.dp))

            val usbDeviceData = vm.usbDeviceData?.collectAsState()?.value
            Text(text = usbDeviceData ?: stringResource(id = R.string.no_data))
        }
    } else {
        Text(text = stringResource(id = R.string.no_device_selected), color = MaterialTheme.colorScheme.error)
    }
}

@Preview(name = "Usb Details data")
@Composable
internal fun UsbDetailsScreenPreview() {
    ScaffoldPreview("Usb Details") {
        UsbDetailsScreen(deviceName = "/usb/device1")
    }
}

@Preview(name = "Usb Details no device")
@Composable
internal fun UsbDetailsScreenPreviewError() {
    ScaffoldPreview("Usb Details") {
        UsbDetailsScreen(deviceName = null)
    }
}