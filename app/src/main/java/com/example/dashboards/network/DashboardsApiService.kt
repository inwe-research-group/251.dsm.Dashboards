package com.example.dashboards.network

import com.example.dashboards.data.NPersonasXTipoDocumento
import retrofit2.http.GET

interface DashboardsApiService {
    @GET("api/v1/persona/NPersonasXTipoDocumento")
    suspend fun getNPersonasXTipoDocumento():List<NPersonasXTipoDocumento>

}