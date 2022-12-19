@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.features_host.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AppDrawer(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    coroutineScope: CoroutineScope,
    drawerItems: List<Pair<String, ImageVector>>,
    onItemClick: (String) -> Unit,
    content: @Composable (name: String, drawerState: DrawerState) -> Unit
) {
    val selectedItem = remember { mutableStateOf(drawerItems.map { it.second }[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.second, contentDescription = null) },
                        label = { Text(item.second.name) },
                        selected = item.second == selectedItem.value,
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            selectedItem.value = item.second
                            onItemClick(item.first)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = { content(selectedItem.value.name, drawerState) }
    )
}

@Preview
@Composable
private fun ComposablePreview() {
    AppDrawer(
        drawerState = rememberDrawerState(DrawerValue.Open),
        coroutineScope = rememberCoroutineScope(),
        drawerItems = listOf(
            "1" to Icons.Default.Home,
            "2" to Icons.Default.PlayArrow
        ),
        onItemClick = {},
        content = { _, _ -> Text(text = "Test content") }
    )
}