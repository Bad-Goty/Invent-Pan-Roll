package com.example.inventpanroll.data.models

import com.google.gson.annotations.SerializedName

data class EquiposResponse(
    @SerializedName("equipos")
    val equipos: List<Equipo>
)