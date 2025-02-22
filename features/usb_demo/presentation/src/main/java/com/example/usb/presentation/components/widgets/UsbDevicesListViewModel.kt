package com.example.usb.presentation.components.widgets

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.hardware.usb.UsbDevice
import android.os.IBinder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usb.presentation.service.UsbService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UsbDevicesListViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {

    private var _usbDevices = MutableStateFlow<List<UsbDevice>?>(null)
    val usbDevices = _usbDevices.asStateFlow()

    private var serviceConnection: ServiceConnection? = null

    init {
        bindToUsbService()
    }

    private fun bindToUsbService() {
        val intent = Intent(application, UsbService::class.java)
        var usbService: UsbService? = null
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                usbService = (binder as? UsbService.UsbBinder)?.getService()
                usbService?.usbDevices?.onEach { devices ->
                    _usbDevices.value = devices
                }?.launchIn(viewModelScope)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                usbService = null
            }
        }
        application.bindService(intent, serviceConnection!!, Context.BIND_AUTO_CREATE)
    }

    override fun onCleared() {
        super.onCleared()
        serviceConnection?.let { application.unbindService(it) }
    }
}