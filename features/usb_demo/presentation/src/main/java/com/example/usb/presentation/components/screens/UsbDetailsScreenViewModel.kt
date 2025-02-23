package com.example.usb.presentation.components.screens

import androidx.lifecycle.ViewModel
import com.example.usb.presentation.service.UsbSerialPortFactory
import com.example.usb.presentation.service.UsbSerialPortFactoryMockImpl
import com.example.usb.presentation.service.UsbServiceRepository
import com.example.usb.presentation.service.UsbServiceRepositoryMockImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map


@HiltViewModel(assistedFactory = UsbDetailsScreenViewModel.Factory::class)
internal class UsbDetailsScreenViewModel @AssistedInject constructor(
    private val usbServiceRepository: UsbServiceRepository,
    private val usbSerialPortFactory: UsbSerialPortFactory,
    @Assisted private val deviceName: String?
) : ViewModel() {
    private var _usbDeviceData = MutableStateFlow<List<String>>(listOf())
    val usbDeviceData: StateFlow<List<String>>? get() = _usbDeviceData

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
            val flow: Flow<String> = usbSerialPortFactory.createSerialDataFlow(deviceName)
            flow.collect { data ->
                _usbDeviceData.value = _usbDeviceData.value + data
            }
        }
    }

    @dagger.assisted.AssistedFactory
    interface Factory {
        fun create(deviceName: String?): UsbDetailsScreenViewModel
    }

    companion object {
        fun mock(deviceName: String?) = UsbDetailsScreenViewModel(
            UsbServiceRepositoryMockImpl(),
            UsbSerialPortFactoryMockImpl(),
            deviceName,
        )
    }
}