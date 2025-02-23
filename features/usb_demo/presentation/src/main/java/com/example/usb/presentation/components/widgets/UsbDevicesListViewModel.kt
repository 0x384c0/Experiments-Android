package com.example.usb.presentation.components.widgets

import androidx.lifecycle.ViewModel
import com.example.usb.presentation.service.UsbServiceRepository
import com.example.usb.presentation.service.UsbServiceRepositoryMockImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class UsbDevicesListViewModel @Inject constructor(
    private val usbServiceRepository: UsbServiceRepository
) : ViewModel() {

    val usbDevices = usbServiceRepository.usbDevices

    init {
        usbServiceRepository.bind()
    }

    override fun onCleared() {
        super.onCleared()
        usbServiceRepository.unbind()
    }

    companion object {
        fun mock() = UsbDevicesListViewModel(usbServiceRepository = UsbServiceRepositoryMockImpl())
    }
}