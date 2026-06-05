package com.motiontracker.app

import android.app.Application
import com.motiontracker.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class MotionTrackerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MotionTrackerApp)
        }
    }
}
