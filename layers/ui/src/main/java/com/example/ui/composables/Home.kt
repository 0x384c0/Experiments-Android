package com.example.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun Home(vm: HomeViewModel = hiltViewModel()) {
    Column {
        vm.state.observeAsState().value?.let {
            Text(it.text)
            Button(onClick = vm::changeState) {
                Text("ChangeState")
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    Home(HomeViewModel(TestInteractorImpl()))
}