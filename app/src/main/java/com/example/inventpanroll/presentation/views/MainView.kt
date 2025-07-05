package com.example.inventpanroll.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.inventpanroll.R
import com.example.inventpanroll.presentation.components.CardComponents
import com.example.inventpanroll.presentation.components.MyScaffold
import com.example.inventpanroll.presentation.viewmodels.EquipoViewModel

@Composable
fun MainView(
    navController: NavController,
    viewModel: EquipoViewModel = hiltViewModel()
) {

    val conteoPorEstado by viewModel.conteoPorEstado.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val error by viewModel.error.observeAsState()

    // Extraer cantidades por status
    val activos = conteoPorEstado.find { it.status == "A" }?.cantidad ?: 0
    val inactivos = conteoPorEstado.find { it.status == "I" }?.cantidad ?: 0
    val mantenimiento = conteoPorEstado.find { it.status == "M" }?.cantidad ?: 0

    // Cargar datos al iniciar
    LaunchedEffect(Unit) {
        viewModel.getConteoPorEstado()
    }

    MyScaffold(
        Title = "Inventario",
        ClickAction = { navController.navigate("camera") }
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            CardComponents(
                Component = "CPU",
                Img = R.drawable.cpu,
                Activos = activos.toString(),
                Inactivos = inactivos.toString(),
                Mantenimientos = mantenimiento.toString(),
                ClickAction = { navController.navigate("cpu") }
            )

            Spacer(Modifier.height(25.dp))

            CardComponents(
                Component = "Monitores",
                Img = R.drawable.pantalla_del_monitor,
                Activos = "51",
                Inactivos = "51",
                Mantenimientos = "0"
            )

            Spacer(Modifier.height(25.dp))

            CardComponents(
                Component = "Impresoras",
                Img = R.drawable.impresora,
                Activos = "51",
                Inactivos = "51",
                Mantenimientos = "0"
            )

            Spacer(Modifier.height(25.dp))

            CardComponents(
                Component = "Tablets",
                Img = R.drawable.tableta,
                Activos = "51",
                Inactivos = "51",
                Mantenimientos = "0"
            )

            Spacer(Modifier.height(25.dp))

            CardComponents(
                Component = "Routers",
                Img = R.drawable.wifi,
                Activos = "51",
                Inactivos = "51",
                Mantenimientos = "0"
            )

        }

    }
}