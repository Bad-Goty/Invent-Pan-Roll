package com.example.inventpanroll.data.models

import com.google.gson.annotations.SerializedName

data class ConteoPorEstado(
    @SerializedName("Status")
    val status: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("cantidad")
    val cantidad: Int
)