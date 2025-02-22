package com.example.usb.presentation.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val semanticsModifier = modifier
        .padding(horizontal = 16.dp, vertical = 4.dp)
    Card(
        modifier = semanticsModifier.clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "ID: " + state.id,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = state.name,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview
@Composable
internal fun PostItemPreview() {
    USBDeviceItem(UsbDeviceState(
        name = "/usb/device/1",
        id = 1234
    ), {})
}


