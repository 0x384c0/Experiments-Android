package com.example.usb.presentation.components.screens

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbManager
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.usb.presentation.components.widgets.UsbDevicesListView
import com.example.usb.presentation.data.UsbDeviceState
import com.example.usb.presentation.navigation.MockUsbDemoRouterImpl
import com.example.utils.ScaffoldPreview
import com.example.utils.hiltViewModelFactory

@Composable
internal fun UsbDemoScreen(vm: UsbDemoScreenViewModel = hiltViewModelFactory(::mockVMFactory)) {
    val context = LocalContext.current
    UsbDevicesListView(onDeviceClick = { usbDevice ->
        checkPermissions(
            context,
            usbDevice,
        ) { vm.toUsbDetails(usbDevice) }
    })
}

private const val ACTION_USB_PERMISSION = "ACTION_USB_PERMISSION"

private fun checkPermissions(context: Context, usbDevice: UsbDeviceState, onPermissionGranted: () -> Unit) {
    val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    val device = usbManager.deviceList[usbDevice.deviceName]

    if (device != null) {
        if (usbManager.hasPermission(device)) {
            onPermissionGranted()
        } else {
            val permissionIntent = PendingIntent.getBroadcast(
                context, 0, Intent(ACTION_USB_PERMISSION), 0
            )
            usbManager.requestPermission(device, permissionIntent)
            val receiver =
                object : BroadcastReceiver() {
                    override fun onReceive(context: Context?, intent: Intent?) {
                        if (intent?.action == ACTION_USB_PERMISSION) {
                            synchronized(this) {
                                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                                    onPermissionGranted()
                                }
                                context?.unregisterReceiver(this)
                            }
                        }
                    }
                }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.registerReceiver(
                    receiver,
                    IntentFilter(ACTION_USB_PERMISSION),
                    Context.RECEIVER_NOT_EXPORTED,
                )
            } else {
                @SuppressLint("UnspecifiedRegisterReceiverFlag")
                context.registerReceiver(
                    receiver,
                    IntentFilter(ACTION_USB_PERMISSION),
                )
            }
        }
    }
}

@Preview
@Composable
internal fun UsbDemoScreenPreview() {
    ScaffoldPreview("USB Demo") { UsbDemoScreen() }
}

private fun mockVMFactory() = UsbDemoScreenViewModel(router = MockUsbDemoRouterImpl())
