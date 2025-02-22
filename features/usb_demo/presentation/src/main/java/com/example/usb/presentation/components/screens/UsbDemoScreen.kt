package com.example.usb.presentation.components.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun UsbDemoScreen(
    vm: UsbDemoViewModel = hiltViewModel(),
    composableScope: CoroutineScope = rememberCoroutineScope()
) {
    Text(text = "USB Demo")
}