package com.example.dashboards.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dashboards.data.NPersonasXTipoDocumento

@Composable
fun DashboardScreen(){
    val viewModel : DashboardViewModel = viewModel(factory = DashboardViewModel.Factory)
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarDashboard()
    }
    val datos : List<NPersonasXTipoDocumento> = uiState.datosDashboard
    //llamar a los screens
    BarrasScreen(datos)
    PieScreen(datos)
}