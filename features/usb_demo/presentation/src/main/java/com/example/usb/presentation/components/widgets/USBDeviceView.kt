package com.example.usb.presentation.components.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.usb.presentation.data.UsbDeviceState

@Composable
internal fun USBDeviceItem(
    state: UsbDeviceState,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val semanticsModifier = modifier
        .padding(horizontal = 16.dp, vertical = 4.dp)
    Card(
        modifier = if (onClick != null) semanticsModifier.clickable(onClick = onClick) else Modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = state.deviceName,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.size(8.dp))

            val fields = listOf(
                "Product Name" to state.productName.toString(),
                "Manufacturer Name" to state.manufacturerName.toString(),
                "Id" to state.deviceId.toString(),
                "Device Class" to state.deviceClass.toString(),
                "Device Subclass" to state.deviceSubclass.toString(),
                "Device Protocol" to state.deviceProtocol.toString(),
                "Configuration Count" to state.configurationCount.toString(),
            )
            for (field in fields) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = field.first + ": ",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline,
                    )

                    Text(
                        text = field.second,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
internal fun PostItemPreview() {
    USBDeviceItem(UsbDeviceState.mock(), {})
}


