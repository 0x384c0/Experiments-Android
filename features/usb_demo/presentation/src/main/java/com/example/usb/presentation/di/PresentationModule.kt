package com.example.usb.presentation.di

import com.example.usb.presentation.navigation.UsbDemoRouter
import com.example.usb.presentation.navigation.UsbDemoRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PresentationModule {
    @Binds
    abstract fun provideUsbDemoInteractor(impl: UsbDemoRouterImpl): UsbDemoRouter
}