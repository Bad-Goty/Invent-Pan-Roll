package com.example.inventpanroll.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inventpanroll.R
import com.example.inventpanroll.data.models.Equipo

@Composable
fun CardCPUs(navController: NavController, equipo: Equipo) {

    val gradient = remember {
        Brush.verticalGradient(
            colors = listOf(Color.Gray, Color.White)
        )
    }

    Card(
        Modifier.fillMaxWidth()
            .height(150.dp)
            .clickable(
                onClick = {
                    navController.navigate("detailscpu/${equipo.noSerie}")
                }
            ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = gradient
                )
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(16.dp)
                ),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.dell),
                contentDescription = "Marca",
                modifier = Modifier
                    .size(120.dp)
                    .padding(16.dp)
                    .weight(1f)
            )

            VerticalDivider()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .weight(2f)
            ) {
                Text(
                    text = equipo.noSerie,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                HorizontalDivider()

                Text(
                    text = "Encargado: ${equipo.responsable}",
                    color = Color.Black,
                )

                Text(
                    text = "Area: ${equipo.area}",
                    color = Color.Black,
                )

                Text(
                    text = "Modelo: ${equipo.modelo}",
                    color = Color.Black,
                )
            }
        }
    }
}