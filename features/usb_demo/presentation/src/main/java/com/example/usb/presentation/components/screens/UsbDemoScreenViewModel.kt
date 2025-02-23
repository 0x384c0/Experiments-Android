package com.example.usb.presentation.components.screens

import androidx.lifecycle.ViewModel
import com.example.usb.presentation.data.UsbDeviceState
import com.example.usb.presentation.navigation.MockUsbDemoRouterImpl
import com.example.usb.presentation.navigation.UsbDemoRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class UsbDemoScreenViewModel @Inject constructor(
    private val router: UsbDemoRouter,
) : ViewModel() {
    fun toUsbDetails(usbDevice: UsbDeviceState) = router.usbDetails(usbDevice.deviceName)

    companion object {
        fun mock() = UsbDemoScreenViewModel(router = MockUsbDemoRouterImpl())
    }
}