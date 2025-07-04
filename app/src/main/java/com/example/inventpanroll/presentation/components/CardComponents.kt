package com.example.inventpanroll.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun CardComponents(
    Component: String,
    Img: Int,
    Activos: String,
    Inactivos: String,
    Mantenimientos: String,
    ClickAction: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { ClickAction() },

        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 3.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(16.dp) // importante: igual que el Card
                )
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF4CAF50), Color.White)
                    )
                )
                .padding(8.dp)
        ) {

            Column {
                Text(
                    text = Component,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = Img),
                        contentDescription = "CPU",
                        modifier = Modifier
                            .size(130.dp)
                            .weight(1f)
                    )
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),

                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "Activos: $Activos",
                            fontSize = 18.sp,
                            color = Color.Black
                        )

                        Text(
                            text = "Inactivos: $Inactivos",
                            fontSize = 18.sp,
                            color = Color.Black
                        )

                        Text(
                            text = "Mantenimientos: $Mantenimientos",
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
