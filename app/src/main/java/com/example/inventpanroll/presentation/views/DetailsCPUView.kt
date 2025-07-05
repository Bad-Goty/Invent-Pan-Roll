package com.example.inventpanroll.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.inventpanroll.presentation.components.MyScaffold
import com.example.inventpanroll.presentation.viewmodels.EquipoViewModel

@Composable
fun DetailsCPUView(
    noSerie: String,
    viewModel: EquipoViewModel = hiltViewModel()
) {
    val equipo by viewModel.selectedEquipo.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getEquipoByNoSerie(noSerie)
    }

    MyScaffold(
        Title = noSerie,
        Icono = Icons.Default.Edit,
        ClickAction = { println(noSerie) }
    ) {
        Column {
            Text(
                "Datos del Equipo",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )

            Spacer(Modifier.height(20.dp))

            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                error != null -> {
                    Text("Error: $error", color = Color.Red)
                }

                equipo != null -> {
                    // Aquí muestras los datos del equipo
                    TextoDetailsCPU("Responsable", equipo!!.responsable)
                    TextoDetailsCPU("Marca", equipo!!.marca)
                    TextoDetailsCPU("Modelo", equipo!!.modelo)
                    TextoDetailsCPU("RAM", equipo!!.ram)
                    TextoDetailsCPU("ROM", equipo!!.rom)
                    TextoDetailsCPU("CPU", equipo!!.cpu)
                    TextoDetailsCPU("SO", equipo!!.so)
                    TextoDetailsCPU("IP", equipo!!.ip)
                    TextoDetailsCPU("Área", equipo!!.area)
                }

                else -> {
                    Text("No se encontró el equipo")
                }
            }
        }
    }
}

@Composable
fun TextoDetailsCPU(label: String, value: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(label)
            }
            append(": $value")
        },
        color = Color.Black,
        fontSize = 20.sp
    )

    HorizontalDivider()
}