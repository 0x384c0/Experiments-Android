package com.example.usb.presentation.service

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.usb.presentation.data.UsbDeviceState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

internal interface UsbServiceRepository {
    val usbDevices: StateFlow<List<UsbDeviceState>?>
    fun bind()
    fun unbind()
    suspend fun readData(deviceName: String): String?
}

internal class UsbServiceRepositoryImpl @Inject constructor(
    private val application: Application
) : UsbServiceRepository {
    private val _usbDevices = MutableStateFlow<List<UsbDeviceState>?>(null)
    override val usbDevices: StateFlow<List<UsbDeviceState>?> get() = _usbDevices

    private var serviceConnection: ServiceConnection? = null
    var usbService: UsbService? = null

    override fun bind() {
        val intent = Intent(application, UsbService::class.java)
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                usbService = (binder as? UsbService.UsbBinder)?.getService()
                usbService?.usbDevices?.onEach { devices ->
                    _usbDevices.value = devices.map { UsbDeviceState(usbDevice = it) }
                }?.launchIn(CoroutineScope(Dispatchers.IO))
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                usbService = null
            }
        }
        application.bindService(intent, serviceConnection!!, Context.BIND_AUTO_CREATE)
    }

    override fun unbind() {
        serviceConnection?.let { application.unbindService(it) }
    }

    override suspend fun readData(deviceName: String): String? = usbService?.readData(deviceName = deviceName)
}

internal class UsbServiceRepositoryMockImpl : UsbServiceRepository {
    override val usbDevices = MutableStateFlow<List<UsbDeviceState>?>(listOf(
        UsbDeviceState.mock(),
        UsbDeviceState.mock(),
    ))

    override fun bind() {}
    override fun unbind() {}
    override suspend fun readData(deviceName: String): String? = "Example USB device data"
}