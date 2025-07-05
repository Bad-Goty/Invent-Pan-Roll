package com.example.inventpanroll.data.models

import com.google.gson.annotations.SerializedName

data class ConteoResponse(
    @SerializedName("conteoPorEstado")
    val conteoPorEstado: List<ConteoPorEstado>
)