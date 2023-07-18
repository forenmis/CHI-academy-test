package com.example.chiacademytest.app

import android.app.Application
import android.content.Context
import com.example.chiacademytest.database.DatabaseManager

class App : Application() {

    lateinit var databaseManager: DatabaseManager

    override fun onCreate() {
        super.onCreate()
        databaseManager = DatabaseManager(this)
    }

    companion object {
        fun getInstance(context: Context): App = context.applicationContext as App
    }
}