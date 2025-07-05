package com.example.inventpanroll.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventpanroll.data.models.ConteoPorEstado
import com.example.inventpanroll.data.models.Equipo
import com.example.inventpanroll.data.repository.EquipoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipoViewModel @Inject constructor(
    private val repository: EquipoRepository
) : ViewModel() {

    private val _equipos = MutableLiveData<List<Equipo>>()
    val equipos: LiveData<List<Equipo>> = _equipos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _selectedEquipo = MutableLiveData<Equipo?>()
    val selectedEquipo: LiveData<Equipo?> = _selectedEquipo

    private val _conteoPorEstado = MutableLiveData<List<ConteoPorEstado>>()
    val conteoPorEstado: LiveData<List<ConteoPorEstado>> = _conteoPorEstado

    fun getEquipos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            repository.getEquipos().fold(
                onSuccess = { equiposList ->
                    _equipos.value = equiposList
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _error.value = exception.message
                    _isLoading.value = false
                }
            )
        }
    }

    fun getConteoPorEstado() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            repository.getConteoPorEstado().fold(
                onSuccess = { conteoList ->
                    _conteoPorEstado.value = conteoList
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _error.value = exception.message
                    _isLoading.value = false
                }
            )
        }
    }

    fun getEquipoByNoSerie(noSerie: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            repository.getEquipoByNoSerie(noSerie).fold(
                onSuccess = { equipo ->
                    _selectedEquipo.value = equipo
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _error.value = exception.message
                    _isLoading.value = false
                }
            )
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun clearSelectedEquipo() {
        _selectedEquipo.value = null
    }
}