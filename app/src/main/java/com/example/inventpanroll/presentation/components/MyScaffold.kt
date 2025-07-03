package com.example.inventpanroll.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(
    Title: String,
    Icono: ImageVector = Icons.Default.QrCode2,
    ClickAction: () -> Unit,
    Content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = Title, fontWeight = FontWeight.ExtraBold, fontSize = 35.sp) },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1FB0FF),
                    titleContentColor = Color.White
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { ClickAction() },

                containerColor = Color(0xFF1FB0FF),
                contentColor = Color.White,
                modifier = Modifier.size(50.dp)
            ) {
                Icon(imageVector = Icono, contentDescription = "QR Code", modifier = Modifier.size(40.dp))
            }
        }

    ) { innerpadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerpadding).background(Color(0xFFDEEDF6))) {
            Content()
        }
    }
}