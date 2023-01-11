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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.features_host.presentation.R
import com.example.presentation.data.DrawerItemState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AppDrawer(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    coroutineScope: CoroutineScope,
    drawerItems: List<DrawerItemState>,
    onItemClick: (String) -> Unit,
    content: @Composable (name: String, drawerState: DrawerState) -> Unit
) {
    val selectedItem = remember { mutableStateOf(drawerItems.map { it }[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(stringResource(id = item.label)) },
                        selected = item == selectedItem.value,
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            selectedItem.value = item
                            onItemClick(item.route)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = { content(stringResource(id = selectedItem.value.label), drawerState) }
    )
}

@Preview
@Composable
private fun ComposablePreview() {
    AppDrawer(
        drawerState = rememberDrawerState(DrawerValue.Open),
        coroutineScope = rememberCoroutineScope(),
        drawerItems = listOf(
            DrawerItemState(
                Icons.Default.Home,
                R.string.drawer_home,
                "",
            ),
            DrawerItemState(
                Icons.Default.PlayArrow,
                R.string.drawer_animations,
                "",
            )
        ),
        onItemClick = {},
        content = { _, _ -> Text(text = "Test content") }
    )
}