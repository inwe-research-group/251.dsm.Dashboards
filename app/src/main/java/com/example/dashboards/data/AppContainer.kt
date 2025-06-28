package com.example.dashboards.data

import com.example.dashboards.network.DashboardsApiService
import com.example.dashboards.repository.DashboardRepository
import com.example.dashboards.repository.DefaultDashboardRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val dashboardRepository: DashboardRepository
}
class DefaultAppContainer: AppContainer{
    private val BASE_URL= "http://192.168.56.1:8080" //no usar localhost
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val dashboardsApiService: DashboardsApiService by lazy{
        retrofit.create(DashboardsApiService::class.java)
    }
    override val dashboardRepository: DashboardRepository by lazy{
        DefaultDashboardRepository(dashboardsApiService)
    }



}