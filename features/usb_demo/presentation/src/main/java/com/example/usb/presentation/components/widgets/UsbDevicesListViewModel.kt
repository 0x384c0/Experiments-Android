package com.example.usb.presentation.components.widgets

import androidx.lifecycle.ViewModel
import com.example.usb.presentation.service.UsbServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsbDevicesListViewModel @Inject constructor(
    private val usbServiceManager: UsbServiceRepository
) : ViewModel() {

    val usbDevices = usbServiceManager.usbDevices

    init {
        usbServiceManager.bind()
    }

    override fun onCleared() {
        super.onCleared()
        usbServiceManager.unbind()
    }
}