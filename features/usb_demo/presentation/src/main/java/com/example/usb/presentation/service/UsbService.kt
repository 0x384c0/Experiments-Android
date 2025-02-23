package com.example.usb.presentation.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Binder
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class UsbService : Service() {

    @Inject
    lateinit var usbManager: UsbManager

    private val _usbDevices = MutableStateFlow<List<UsbDevice>>(emptyList())
    val usbDevices: StateFlow<List<UsbDevice>> = _usbDevices.asStateFlow()

    private val usbReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == UsbManager.ACTION_USB_DEVICE_ATTACHED ||
                intent?.action == UsbManager.ACTION_USB_DEVICE_DETACHED
            ) {
                updateDeviceList()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        registerReceiver(usbReceiver, IntentFilter().apply {
            addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
            addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        })
        updateDeviceList()
    }

    override fun onDestroy() {
        unregisterReceiver(usbReceiver)
        super.onDestroy()
    }

    private fun updateDeviceList() {
        _usbDevices.value = usbManager.deviceList.values.toList()
    }

    override fun onBind(intent: Intent?): IBinder = UsbBinder()

    inner class UsbBinder : Binder() {
        fun getService(): UsbService = this@UsbService
    }

    suspend fun readData(deviceName: String): String? = withContext(Dispatchers.IO) {
        val usbDevice = usbManager.deviceList?.values?.find { it.deviceName == deviceName }
        usbDevice?.let { device ->
            val connection = usbManager.openDevice(device)
            connection?.let {
                val buffer = ByteArray(64)
                val bytesRead = it.bulkTransfer(device.getInterface(0).getEndpoint(0), buffer, buffer.size, 1000)
                it.close()
                if (bytesRead > 0) {
                    return@withContext String(buffer, 0, bytesRead)
                }
            }
        }
        return@withContext null
    }
}