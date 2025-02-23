package com.example.usb.presentation.components.screens

import androidx.lifecycle.ViewModel
import com.example.usb.presentation.service.UsbServiceRepository
import com.example.usb.presentation.service.UsbServiceRepositoryMockImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map


@HiltViewModel(assistedFactory = UsbDetailsScreenViewModel.Factory::class)
internal class UsbDetailsScreenViewModel @AssistedInject constructor(
    private val usbServiceRepository: UsbServiceRepository,
    @Assisted private val deviceName: String?
) : ViewModel() {
    private var _usbDeviceData = MutableStateFlow<String?>(null)
    val usbDeviceData: StateFlow<String?>? get() = _usbDeviceData

    val usbDevice = usbServiceRepository.usbDevices.map { it?.find { it.deviceName == deviceName } }

    init {
        usbServiceRepository.bind()
    }

    override fun onCleared() {
        super.onCleared()
        usbServiceRepository.unbind()
    }

    suspend fun readData() {
        deviceName?.let {
            _usbDeviceData.value = usbServiceRepository.readData(deviceName)
        }
    }

    @dagger.assisted.AssistedFactory
    interface Factory {
        fun create(deviceName: String?): UsbDetailsScreenViewModel
    }

    companion object {
        fun mock(deviceName: String?) = UsbDetailsScreenViewModel(UsbServiceRepositoryMockImpl(), deviceName)
    }
}