package com.example.inventpanroll.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inventpanroll.R
import com.example.inventpanroll.presentation.components.CardComponents
import com.example.inventpanroll.presentation.components.MyScaffold

@Composable
fun MainView(navController: NavController) {
    MyScaffold(
        Title = "Inventario",
        ClickAction = { println("Hola") }
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
                Activos = "51",
                Inactivos = "51",
                Mantenimientos = "0",
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