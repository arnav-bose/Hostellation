package com.arnav.hostellation

import android.app.Application
import com.arnav.core.data.network.NetworkClient
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HostellationApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKits()
    }

    private fun initKits() {
        NetworkClient.initializeNetworkClient()
    }

}