package com.example.usb.presentation.data

import android.hardware.usb.UsbDevice

internal data class UsbDeviceState(
    val deviceName: String,
    val productName: String?,
    val manufacturerName: String?,
    val deviceId: Int,
    val deviceClass: Int,
    val deviceSubclass: Int,
    val deviceProtocol: Int,
    val configurationCount: Int,
) {
    constructor(usbDevice: UsbDevice) : this(
        deviceName = usbDevice.deviceName,
        productName = usbDevice.productName,
        manufacturerName = usbDevice.manufacturerName,
        deviceId = usbDevice.deviceId,
        deviceClass = usbDevice.deviceClass,
        deviceSubclass = usbDevice.deviceSubclass,
        deviceProtocol = usbDevice.deviceProtocol,
        configurationCount = usbDevice.configurationCount,
    )

    companion object {
        fun mock() = UsbDeviceState(
            deviceName = "/usb/device1",
            productName = "Product Name",
            manufacturerName = "Manufacturer Name",
            deviceId = 1,
            deviceClass = 2,
            deviceSubclass = 3,
            deviceProtocol = 4,
            configurationCount = 5,
        )
    }
}