package com.example.presentation.components.item

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.presentation.R


@Composable
fun CircleImage(
    imageUri: Uri,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
        painter = rememberAsyncImagePainter(
            model = imageUri,
            placeholder = rememberVectorPainter(image = Icons.Default.CloudDownload),
            fallback = rememberVectorPainter(image = Icons.Default.Error),
        ),
        contentDescription = "",
    )
}