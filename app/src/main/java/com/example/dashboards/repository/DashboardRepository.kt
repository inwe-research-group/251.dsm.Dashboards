package com.example.dashboards.repository

import com.example.dashboards.data.NPersonasXTipoDocumento
import com.example.dashboards.network.DashboardsApiService

interface DashboardRepository {
    suspend fun getNPersonasXTipoDocumento():List<NPersonasXTipoDocumento>
}
class DefaultDashboardRepository(
    private val dashboardsApiService: DashboardsApiService
): DashboardRepository {

    override suspend fun getNPersonasXTipoDocumento(): List<NPersonasXTipoDocumento> {
        return dashboardsApiService.getNPersonasXTipoDocumento()
    }

}