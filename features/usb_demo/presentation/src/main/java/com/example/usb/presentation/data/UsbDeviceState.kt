package com.example.usb.presentation.data

import android.hardware.usb.UsbDevice

internal data class UsbDeviceState(
    val name: String,
    val productName: String?,
    val manufacturerName: String?,
    val id: Int,
    val deviceClass: Int,
    val deviceSubclass: Int,
    val deviceProtocol: Int,
    val configurationCount: Int,
) {
    constructor(usbDevice: UsbDevice) : this(
        name = usbDevice.deviceName,
        productName = usbDevice.productName,
        manufacturerName = usbDevice.manufacturerName,
        id = usbDevice.deviceId,
        deviceClass = usbDevice.deviceClass,
        deviceSubclass = usbDevice.deviceSubclass,
        deviceProtocol = usbDevice.deviceProtocol,
        configurationCount = usbDevice.configurationCount,
    )

    companion object {
        fun mock() = UsbDeviceState(
            name = "/usb/device1",
            productName = "Product Name",
            manufacturerName = "Manufacturer Name",
            id = 1,
            deviceClass = 2,
            deviceSubclass = 3,
            deviceProtocol = 4,
            configurationCount = 5,
        )
    }
}