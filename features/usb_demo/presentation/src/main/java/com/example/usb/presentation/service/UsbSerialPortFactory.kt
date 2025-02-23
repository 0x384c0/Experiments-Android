package com.example.usb.presentation.service

import android.hardware.usb.UsbManager
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProber
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.nio.charset.StandardCharsets
import javax.inject.Inject

internal interface UsbSerialPortFactory {
    fun createSerialDataFlow(
        deviceName: String,
        port: Int = 0,
        baudRate: Int = 115200,
        dataBits: Int = 8,
    ): Flow<String>
}

internal class UsbSerialPortFactoryImpl @Inject constructor(
    private var usbManager: UsbManager,
) : UsbSerialPortFactory {
    override fun createSerialDataFlow(
        deviceName: String,
        port: Int,
        baudRate: Int,
        dataBits: Int,
    ): Flow<String> = flow {
        val usbDevice = usbManager.deviceList?.values?.find { it.deviceName == deviceName } ?: throw Exception("USB device not found")

        val driver = UsbSerialProber.getDefaultProber().probeDevice(usbDevice) ?: throw Exception("USB driver not found")

        val connection = usbManager.openDevice(driver.device) ?: throw Exception("Failed to open USB connection")

        val port: UsbSerialPort = driver.ports[port]  // Use the first serial port
        port.open(connection)
        port.setParameters(
            baudRate,
            dataBits,
            UsbSerialPort.STOPBITS_1,
            UsbSerialPort.PARITY_NONE
        )

        val buffer = ByteArray(64)  // Buffer for incoming data
        try {
            while (true) {  // Keep reading continuously
                val bytesRead = port.read(buffer, 1000)  // Read with timeout (1 sec)
                if (bytesRead > 0) {
                    val receivedData = String(buffer, 0, bytesRead, StandardCharsets.UTF_8)
                    emit(receivedData)  // Emit received data as a Flow
                }
                delay(100)  // Small delay to avoid overloading
            }
        } catch (e: Exception) {
            throw Exception("Error reading data: ${e.message}")
        } finally {
            port.close()
            connection.close()
        }
    }
}

internal class UsbSerialPortFactoryMockImpl @Inject constructor() : UsbSerialPortFactory {
    override fun createSerialDataFlow(
        deviceName: String,
        port: Int,
        baudRate: Int,
        dataBits: Int,
    ): Flow<String> = flow {
        emit("Test data 1")
        emit("Test data 2")
        emit("Test data 3")
    }
}