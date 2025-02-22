package com.example.usb.presentation.data

import android.hardware.usb.UsbDevice

internal data class UsbDeviceState(
    val name: String,
    val id: Int,
) {
    constructor(usbDevice: UsbDevice) : this(usbDevice.deviceName, usbDevice.deviceId)
}