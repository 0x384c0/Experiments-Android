package com.example.features_host.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.features_host.presentation.components.App
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}