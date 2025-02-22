package com.example.usb.presentation.di

import android.content.Context
import android.hardware.usb.UsbManager
import com.example.usb.presentation.navigation.UsbDemoRouter
import com.example.usb.presentation.navigation.UsbDemoRouterImpl
import com.example.usb.presentation.service.UsbServiceRepository
import com.example.usb.presentation.service.UsbServiceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PresentationModule {
    @Binds
    abstract fun provideUsbDemoInteractor(impl: UsbDemoRouterImpl): UsbDemoRouter

    @Binds
    abstract fun provideUsbServiceRepository(impl: UsbServiceRepositoryImpl): UsbServiceRepository

    companion object {
        @Provides
        fun provideUsbManager(@ApplicationContext context: Context): UsbManager {
            return context.getSystemService(Context.USB_SERVICE) as UsbManager
        }
    }
}