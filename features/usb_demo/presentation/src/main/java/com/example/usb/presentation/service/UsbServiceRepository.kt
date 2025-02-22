package com.example.usb.presentation.service

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.hardware.usb.UsbDevice
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class UsbServiceRepository @Inject constructor(
    private val application: Application
) {
    private val _usbDevices = MutableStateFlow<List<UsbDevice>?>(null)
    val usbDevices: StateFlow<List<UsbDevice>?> get() = _usbDevices

    private var serviceConnection: ServiceConnection? = null


    fun bind() {
        val intent = Intent(application, UsbService::class.java)
        var usbService: UsbService? = null
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                usbService = (binder as? UsbService.UsbBinder)?.getService()
                usbService?.usbDevices?.onEach { devices ->
                    _usbDevices.value = devices
                }?.launchIn(CoroutineScope(Dispatchers.IO))
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                usbService = null
            }
        }
        application.bindService(intent, serviceConnection!!, Context.BIND_AUTO_CREATE)
    }

    fun unbind() {
        serviceConnection?.let { application.unbindService(it) }
    }
}