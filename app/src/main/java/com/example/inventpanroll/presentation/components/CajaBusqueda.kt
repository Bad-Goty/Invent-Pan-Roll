package com.example.inventpanroll.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CajaBusqueda() {
    var text by rememberSaveable { mutableStateOf("") }
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(16.dp))
        .background(Color(0xFF525B60)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            placeholder = { Text("Que buscas?") },
            singleLine = true,
            modifier = Modifier.weight(1f),
            trailingIcon = { // leading y trailing icon, uno es paa ponelo al principio y el otro al final
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,

                unfocusedPlaceholderColor = Color.LightGray,
                focusedPlaceholderColor = Color.White,

                focusedTextColor = Color.White,

                cursorColor = Color(0xFF1FB0FF),

                selectionColors = TextSelectionColors(
                    handleColor = Color(0xFF1FB0FF),
                    backgroundColor = Color(0xFF1FB0FF)
                )
            )
        )

        IconButton(
            onClick = {}
        ) {
            Icon(Icons.Default.Menu, contentDescription = "Filtrar")
        }
    }
}