package com.example.inventpanroll.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.inventpanroll.presentation.components.CajaBusqueda
import com.example.inventpanroll.presentation.components.CardCPUs
import com.example.inventpanroll.presentation.components.MyScaffold
import com.example.inventpanroll.presentation.viewmodels.EquipoViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CPUView(
    navController: NavController,
    viewModel: EquipoViewModel = hiltViewModel()
) {

    val equipos by viewModel.equipos.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getEquipos()
    }

    MyScaffold(
        Title = "CPU",
        ClickAction = { println("hola") }
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
        ) {
            CajaBusqueda()

            Spacer(Modifier.height(20.dp))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                error != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error: $error",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                viewModel.clearError()
                                viewModel.getEquipos()
                            }
                        ) {
                            Text("Reintentar")
                        }
                    }
                }

                equipos.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No hay equipos disponibles")
                    }
                }

                else -> {
                    /*LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(equipos) { equipo ->
                            CardCPUs(navController, equipo = equipo)
                        }
                    } -----ANTES*/

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(equipos, key = {it.noSerie}) { equipo ->
                            CardCPUs(navController, equipo = equipo)
                        }
                    }
                }

            }
        }
    }
}