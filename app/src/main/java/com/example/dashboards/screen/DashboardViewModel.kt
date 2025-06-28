package com.example.dashboards.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.dashboards.DashboardApplication
import com.example.dashboards.data.NPersonasXTipoDocumento
import com.example.dashboards.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DashboardUiState(
    val datosDashboard: List<NPersonasXTipoDocumento> = listOf(),
    val flag_error_dashboard: Boolean=false,
)
class DashboardViewModel(private val dashboardRepository: DashboardRepository) : ViewModel(){
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()
    /*
    fun cargarDashboard(){
        viewModelScope.launch {
            try{
                val dashboard_get=dashboardRepository.getNPersonasXTipoDocumento()
                _uiState.value=_uiState.value.copy(datosDashboard = dashboard_get)
            }catch(e: Exception){
                _uiState.value=_uiState.value.copy(flag_error_dashboard = true)
            }catch(e: HttpException){
                _uiState.value=_uiState.value.copy(flag_error_dashboard = true)
            }
        }

    }*/
    fun cargarDashboard() {
       viewModelScope.launch {
           try {
               val dashboard_get = dashboardRepository.getNPersonasXTipoDocumento()

               // ✅ Mostrar los datos en consola
               println("✅ Datos recibidos:")
               dashboard_get.forEach {
                   println("Descripción: ${it.descripcion}, Cantidad: ${it.cantidad}")
               }

               _uiState.value = _uiState.value.copy(datosDashboard = dashboard_get)
           } catch (e: Exception) {
               println("❌ Error general al cargar dashboard: ${e.message}")
               _uiState.value = _uiState.value.copy(flag_error_dashboard = true)
           } catch (e: HttpException) {
               println("❌ Error HTTP al cargar dashboard: ${e.message}")
               _uiState.value = _uiState.value.copy(flag_error_dashboard = true)
           }
       }
   }

    fun resetFlags(){
        _uiState.value=_uiState.value.copy(flag_error_dashboard = false)
    }

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as DashboardApplication)
                val dashboardRepository = application.container.dashboardRepository
                DashboardViewModel(dashboardRepository = dashboardRepository)
            }

        }

    }
}