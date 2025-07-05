package com.example.inventpanroll.data.repository

import com.example.inventpanroll.data.models.ConteoPorEstado
import com.example.inventpanroll.data.models.Equipo
import com.example.inventpanroll.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipoRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getEquipos(): Result<List<Equipo>> {
        return try {
            val response = apiService.getEquipos()
            if (response.isSuccessful) {
                Result.success(response.body()?.equipos ?: emptyList())
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getConteoPorEstado(): Result<List<ConteoPorEstado>> {
        return try {
            val response = apiService.getConteoCpu()
            if (response.isSuccessful){
                Result.success(response.body()?.conteoPorEstado ?: emptyList())
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getEquipoByNoSerie(noSerie: String): Result<Equipo> {
        return try {
            val response = apiService.getEquipos() // Usa el mismo endpoint
            if (response.isSuccessful) {
                val equipo = response.body()?.equipos?.find { it.noSerie == noSerie }
                equipo?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Equipo no encontrado"))
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}