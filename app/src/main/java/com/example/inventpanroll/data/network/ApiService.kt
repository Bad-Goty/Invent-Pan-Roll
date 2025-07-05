package com.example.inventpanroll.data.network

import com.example.inventpanroll.data.models.ConteoResponse
import com.example.inventpanroll.data.models.Equipo
import com.example.inventpanroll.data.models.EquiposResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/EquiposActivos")
    suspend fun getEquipos(): Response<EquiposResponse>

    @GET("api/EquiposActivos/{noSerie}") // Si necesitas obtener un equipo específico
    suspend fun getEquipoByNoSerie(@Path("noSerie") noSerie: String): Response<Equipo>

    // Agrega más endpoints según necesites

    @GET("api/ConteoEquiposPorEstado")
    suspend fun getConteoCpu(): Response<ConteoResponse>
}