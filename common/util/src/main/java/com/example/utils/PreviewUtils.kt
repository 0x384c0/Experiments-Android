package com.example.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel

/**
 * A utility function to create a scaffold layout for previewing composables.
 *
 * @param title The title to be displayed in the top app bar.
 * @param content The composable content to be displayed within the scaffold.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldPreview(title: String, content: @Composable () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(title) }) },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    )
}

/**
 * A utility function to provide a ViewModel instance for previews and live views.
 *
 * @param mockVM A mock ViewModel instance.
 * @return A ViewModel instance
 */
@Composable
inline fun <reified VM : ViewModel> hiltViewModelWithPreview(mockVM: VM): VM = if (LocalInspectionMode.current) mockVM else hiltViewModel<VM>()

/**
 * A utility function to provide a ViewModel instance for previews and live views.
 *
 * @param mockVM A mock ViewModel instance.
 * @return A ViewModel instance ViewModel with an @AssistedInject-annotated constructor
 */
@Composable
inline fun <reified VM : ViewModel, reified VMF> hiltViewModelWithPreview(
    mockVM: VM,
    noinline creationCallback: (VMF) -> VM
): VM = if (LocalInspectionMode.current) mockVM else hiltViewModel<VM, VMF>(creationCallback = creationCallback)