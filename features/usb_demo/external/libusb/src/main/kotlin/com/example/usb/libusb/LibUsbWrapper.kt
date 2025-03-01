package com.example.usb.libusb

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbManager
import com.sun.jna.Library
import com.sun.jna.Native

object LibUsbWrapper {
    interface JNA : Library {
        @Suppress("FunctionName")
        fun set_the_native_Descriptor(fileDescriptor: Int)

        companion object {
            val INSTANCE: JNA = Native.load("libusb1.0", JNA::class.java)
        }
    }

    fun transferFileDescriptorToNative(context: Context, usbDevice: UsbDevice) {
        val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
        val usbDeviceConnection: UsbDeviceConnection = usbManager.openDevice(usbDevice)
        val fileDescriptor: Int = usbDeviceConnection.fileDescriptor
        JNA.INSTANCE.set_the_native_Descriptor(fileDescriptor)
    }
}
