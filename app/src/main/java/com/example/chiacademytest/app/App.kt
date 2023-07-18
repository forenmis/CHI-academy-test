package com.example.chiacademytest.app

import android.app.Application
import android.content.Context
import com.example.chiacademytest.data.network.NetworkManager

class App : Application() {
    lateinit var networkManager: NetworkManager
        private set

    override fun onCreate() {
        super.onCreate()
        networkManager = NetworkManager()
    }

    companion object {
        fun getInstance(context: Context): App = context.applicationContext as App
    }
}