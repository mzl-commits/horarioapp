package com.example.horarioapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import com.google.firebase.FirebaseApp

@HiltAndroidApp
class HorarioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            FirebaseApp.initializeApp(this)
        } catch (e: Exception) {
            // Silently fail if configuration is missing
            // The app will continue but Firebase features will fail gracefully
        }
    }
}