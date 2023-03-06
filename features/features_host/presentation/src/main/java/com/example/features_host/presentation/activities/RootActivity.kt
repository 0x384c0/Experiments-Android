package com.example.features_host.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.features_host.presentation.components.App
import com.example.features_host.presentation.di.FeatureHostModule
import com.example.features_host.presentation.navigation.FeatureHostRouter
import dagger.hilt.EntryPoints
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : ComponentActivity() {

    private val routerFactory get() = EntryPoints.get(applicationContext, FeatureHostRouter.Factory::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            FeatureHostModule.setNavController(navController)
            App(
                navController = navController,
                router = routerFactory.createRouter()
            )
        }
    }
}

