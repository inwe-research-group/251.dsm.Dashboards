package com.example.dashboards

import android.app.Application
import com.example.dashboards.data.AppContainer
import com.example.dashboards.data.DefaultAppContainer

class DashboardApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container=DefaultAppContainer()
    }
}