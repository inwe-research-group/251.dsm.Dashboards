package com.example.dashboards.data

import kotlinx.serialization.Serializable

@Serializable
data class NPersonasXTipoDocumento(
    val descripcion:String,
    val cantidad: Float

)
